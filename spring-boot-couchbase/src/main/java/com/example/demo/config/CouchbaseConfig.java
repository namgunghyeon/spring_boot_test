package com.example.demo.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.env.CouchbaseEnvironment;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.example.demo.model.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.query.Consistency;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    protected static final Logger logger = LoggerFactory.getLogger(CouchbaseConfig.class);

    @Autowired
    private CouchbaseSetting couchbaseSetting;

    @Override
    protected List<String> getBootstrapHosts() {
        logger.info("Registering host '{}' for couchbase cluster", couchbaseSetting.getHostName());
        return Arrays.asList(couchbaseSetting.getHostName());
    }

    @Override
    protected String getBucketName() {
        logger.info("Opening bucket '{}'", couchbaseSetting.getBucketName());
        return couchbaseSetting.getBucketName();
    }

    @Override
    protected String getBucketPassword() {
        logger.info("Get bucket password '{}'", couchbaseSetting.getPassword());
        return couchbaseSetting.getPassword();
    }

    @Override
    protected String getUsername() {
        logger.info("Get bucket username '{}'", couchbaseSetting.getUsername());
        return couchbaseSetting.getUsername();
    }

    @Override
    protected CouchbaseEnvironment getEnvironment() {
        DefaultCouchbaseEnvironment.builder().connectTimeout(60000) // by default 5 sec (5000 ms)
                .queryTimeout(20000) // by default 75 sec (75000 ms)
                .socketConnectTimeout(45000); // by default 1 sec (1000 ms)
        return super.getEnvironment();
    }

    @Override
    public Consistency getDefaultConsistency() {
        // By default, READ_YOUR_OWN_WRITES
        // Values: READ_YOUR_OWN_WRITES, STRONGLY_CONSISTENT, UPDATE_AFTER, EVENTUALLY_CONSISTENT
        return Consistency.READ_YOUR_OWN_WRITES;
    }

    @Override
    public String typeKey() {
        // By default, this attribute is named "_class".
        // Spring Data automatically adds to each document an attribute containing the full class name of the entity.
        // This field is the one used by N1QL queries to filter only documents corresponding to the repository’s entity.
        return "type";
    }

    /************
     * This is additional configuration if we want some other objects (PlaceDoc, PhoneDoc) to be stored in other bucket
     ************/

    @Bean(name = "testBucket") // this is to differentiate with the default beans
    public Bucket testBucket() throws Exception {
        return couchbaseCluster().openBucket("test", "123456"); // TODO you can get values from properties
    }

    @Bean(name = "testBucketTemplate") // this is to differentiate with the default beans
    public CouchbaseTemplate testBucketTemplate() throws Exception {
        CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClusterInfo(), // reuse the default bean
                testBucket(), // the bucket is non-default
                mappingCouchbaseConverter(), translationService() // default beans here as well
        );
        template.setDefaultConsistency(getDefaultConsistency());
        return template;
    }

    @Override
    public void configureRepositoryOperationsMapping(RepositoryOperationsMapping baseMapping) {
        try {
            baseMapping // this is already using couchbaseTemplate as default
                    .mapEntity(Test.class, testBucketTemplate());
            // every repository dealing with Place will be backed by placeTemplate()
        } catch (Exception e) {
            throw new RuntimeException("Place bucket could not be configured properly!");
        }
    }
}