package com.example.demo.config;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import javax.annotation.PostConstruct;

@Component
public class LocalCouchbaseCluster {
    @Autowired
    private CouchbaseSetting couchbaseSetting;
    private static Cluster cluster;

    @PostConstruct
    public void init() {
        clusterConnection(couchbaseSetting);
    }

    private static Cluster clusterConnection(CouchbaseSetting couchbaseSetting) {
        if (cluster == null) {
            cluster =  CouchbaseCluster.create(couchbaseSetting.getHostName());
            cluster.authenticate(couchbaseSetting.getHostName(), couchbaseSetting.getPassword());
        }
        return cluster;
    }

    public Bucket getBucket(String bucketName) {
        return cluster.openBucket(bucketName);
    }
}
