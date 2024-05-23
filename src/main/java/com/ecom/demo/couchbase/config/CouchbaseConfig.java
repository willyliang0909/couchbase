package com.ecom.demo.couchbase.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

@Getter
@Setter
@Configuration
@EnableCouchbaseRepositories
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Value("${spring.couchbase.connectionString}")
    private String connectionString;

    @Value("${spring.couchbase.bucket.user}")
    private String userName;

    @Value("${spring.couchbase.bucket.password:password}")
    private String password;

    @Value("${spring.couchbase.bucket.name:travel-sample}")
    private String bucketName;

}
