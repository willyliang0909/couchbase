package com.ecom.demo.couchbase.config;

import com.couchbase.client.core.error.BucketNotFoundException;
import com.couchbase.client.core.error.UnambiguousTimeoutException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.Scope;
import com.couchbase.client.java.env.ClusterEnvironment;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.time.Duration;

@Getter
@Setter
@Slf4j
@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${spring.couchbase.connectionString}")
    private String connectionString;

    @Value("${spring.couchbase.username}")
    private String userName;

    @Value("${spring.couchbase.password}")
    private String password;

    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;

    @Value("${spring.couchbase.scope.name}")
    private String scopeName;

    @Override
    @Bean(destroyMethod = "disconnect")
    public Cluster couchbaseCluster(ClusterEnvironment couchbaseClusterEnvironment) {
        try {
            log.debug("Connecting to Couchbase cluster at {}", connectionString);
            Cluster cluster = Cluster.connect(connectionString, userName, password);
            cluster.waitUntilReady(Duration.ofSeconds(15));
            return cluster;
        } catch (UnambiguousTimeoutException e) {
            log.error("Connection to Couchbase cluster at {} timed out", connectionString);
            throw e;
        } catch (Exception e) {
            log.error(e.getClass().getName());
            log.error("Could not connect to Couchbase cluster at {}", connectionString);
            throw e;
        }
    }

//    @Override
//    protected void configureEnvironment(ClusterEnvironment.Builder builder) {
//        builder.ioConfig(IoConfig.builder()
//                .maxHttpConnections(10)
//                .maxConnectionsPerEndpoint(10)
//                .build());
//    }

    @Bean
    public Bucket getCouchbaseBucket(Cluster cluster) {
        try {
            if (!cluster.buckets().getAllBuckets().containsKey(getBucketName())) {
                log.error("Bucket with name {} does not exist. Creating it now", getBucketName());
                throw new BucketNotFoundException(bucketName);
            }
            return cluster.bucket(getBucketName());
        } catch (Exception e) {
            log.error("Error getting bucket", e);
            throw e;
        }
    }

    @Bean
    public Scope getCouchbaseScope(Bucket bucket) {
        return bucket.scope(getScopeName());
    }

    @Bean(name = "configCollection")
    public Collection collection(Bucket bucket) {
        return bucket.scope("mod").collection("config");
    }

    @Bean
    public Collection collection1(Bucket bucket) {
        return bucket.scope("mod").collection("user");
    }

}
