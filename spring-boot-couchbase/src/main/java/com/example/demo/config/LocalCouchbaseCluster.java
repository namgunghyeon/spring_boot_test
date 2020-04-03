package com.example.demo.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.HttpServerErrorException;

@Configuration
public class LocalCouchbaseCluster {
    private static Cluster cluster;

    static {
        createClusterConnection();
    }

    private static Cluster createClusterConnection() {
        if (cluster == null) {
            cluster =  CouchbaseCluster.create("localhost");
            cluster.authenticate("admin", "123456");
        }
        return cluster;
    }

    public Bucket getBucket(String bucketName) {
        return cluster.openBucket(bucketName);
    }
}
