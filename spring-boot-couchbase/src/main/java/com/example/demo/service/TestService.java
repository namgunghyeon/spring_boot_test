package com.example.demo.service;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.*;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.Statement;
import com.example.demo.model.Test;
import com.example.demo.repository.TestRepository;
import com.example.demo.utils.N1QL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {
    final private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestRepository testRepository;

    @Autowired
    protected ObjectMapper objectMapper;

    public List<Test> getTest(String id) {
        //move to dao
        Statement statement = select("test.*")
                .from(i("test"))
                .where(x("`value`").eq(s("1")))
                .limit(100);
        N1qlQueryResult result = testRepository
                .getCouchbaseOperations()
                .getCouchbaseBucket()
                .query(N1qlQuery.simple(statement));

        List<Test> list = result.allRows().stream().map(item -> {
            JsonNode jsonNode = N1QL.extractJsonResult(item, objectMapper);
            try {
                return new Test().toModel(jsonNode, objectMapper);
            } catch (IOException e) {
                logger.error(e.getLocalizedMessage());
                return null;
            }
        }).collect(Collectors.toList());

        return list;
        //return testRepository.findById("test").orElseGet(null);
    }
}
