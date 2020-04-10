package com.example.demo;

import com.couchbase.client.java.Bucket;

import com.couchbase.client.java.CouchbaseBucket;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.*;
import com.couchbase.mock.BucketConfiguration;
import com.couchbase.mock.CouchbaseMock;
import com.example.demo.config.CouchbaseConfig;
import com.example.demo.config.CouchbaseStartConfig;
import com.example.demo.dao.CouchBaseDAO;
import com.example.demo.dao.DAO;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.any;

@SpringBootTest
public class CouchbaseDAOTests {
    @MockBean(name = "couchbaseTemplateTest")
    private CouchbaseTemplate couchbaseTemplate;

    @Mock
    private Bucket bucket;

    @TestConfiguration
    public static class Config {
        @Qualifier("couchbaseTemplateTest")
        @Bean
        public CouchbaseTemplate couchbaseTemplateTest() {
            return new CouchbaseTemplate(null, null);
        }
    }

    @BeforeAll
     static void start () {
        CouchbaseStartConfig.couchbaseStart();
    }

    @AfterAll
    static void stop () {
        CouchbaseStartConfig.couchbaseStop();
    }

    @Autowired
    private CouchBaseDAO couchBaseDAO;

    @Test
    void testGetData() {
        N1qlQueryResult n1qlQueryResult = new N1qlQueryResult() {
            @Override
            public List<N1qlQueryRow> allRows() {
                return new ArrayList<>();
            }

            @Override
            public Iterator<N1qlQueryRow> rows() {
                return null;
            }

            @Override
            public Object signature() {
                return null;
            }

            @Override
            public N1qlMetrics info() {
                return null;
            }

            @Override
            public JsonObject profileInfo() {
                return null;
            }

            @Override
            public boolean parseSuccess() {
                return false;
            }

            @Override
            public boolean finalSuccess() {
                return false;
            }

            @Override
            public String status() {
                return null;
            }

            @Override
            public List<JsonObject> errors() {
                return null;
            }

            @Override
            public String requestId() {
                return null;
            }

            @Override
            public String clientContextId() {
                return null;
            }

            @Override
            public Iterator<N1qlQueryRow> iterator() {
                return null;
            }
        };

        System.out.println(couchbaseTemplate);
        given(couchbaseTemplate.getCouchbaseBucket()).willReturn(bucket);
        given(couchbaseTemplate.getCouchbaseBucket().query(any(N1qlQuery.class))).willReturn(n1qlQueryResult);
        couchBaseDAO.setCouchbaseTemplate(couchbaseTemplate);
        List<com.example.demo.model.Test> list = couchBaseDAO.getTestData("1");

        Assertions.assertEquals(list.size(), 0);
    }

}
