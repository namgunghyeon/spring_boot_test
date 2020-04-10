package com.example.demo.config;

import com.couchbase.mock.BucketConfiguration;
import com.couchbase.mock.CouchbaseMock;
import java.util.ArrayList;

public class CouchbaseStartConfig {
    private static CouchbaseMock couchbaseMock;

    public static void couchbaseStart() {
        BucketConfiguration bucketConfiguration = new BucketConfiguration();
        bucketConfiguration.numNodes = 1;
        bucketConfiguration.numReplicas = 1;
        bucketConfiguration.numVBuckets = 1024;
        bucketConfiguration.type = com.couchbase.mock.Bucket.BucketType.COUCHBASE;
        bucketConfiguration.name = "test";
        bucketConfiguration.password = "password";

        ArrayList<BucketConfiguration> configList = new ArrayList<BucketConfiguration>();
        configList.add(bucketConfiguration);

        try {
            couchbaseMock = new CouchbaseMock(8091, configList);
            couchbaseMock.start();
            couchbaseMock.waitForStartup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void couchbaseStop() {
        couchbaseMock.stop();
    }
}
