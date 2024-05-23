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

    @Value("${spring.couchbase.username}")
    private String userName;

    @Value("${spring.couchbase.password}")
    private String password;

    @Value("${spring.couchbase.bucket.name}")
    private String bucketName;

}
