package com.example.demo;

import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.env.DefaultCouchbaseEnvironment;
import com.couchbase.mock.Bucket;
import com.couchbase.mock.BucketConfiguration;
import com.couchbase.mock.CouchbaseMock;
import com.couchbase.mock.client.MockClient;
import com.example.demo.config.CouchbaseStartConfig;
import org.junit.jupiter.api.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;


@SpringBootTest
class DemoApplicationTests {
    @BeforeAll
    static void start () {
        CouchbaseStartConfig.couchbaseStart();
    }

    @AfterAll
    static void stop () {
        CouchbaseStartConfig.couchbaseStop();
    }

    @Test
    void contextLoads() {
    }
}
