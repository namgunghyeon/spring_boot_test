package com.example.demo.dao;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;
import com.example.demo.model.Test;
import com.example.demo.utils.N1QL;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.couchbase.client.java.query.Select.select;
import static com.couchbase.client.java.query.dsl.Expression.i;
import static com.couchbase.client.java.query.dsl.Expression.s;
import static com.couchbase.client.java.query.dsl.Expression.x;

@Service
public class CouchBaseDAO extends DAO {
    final private Logger logger = LoggerFactory.getLogger(CouchBaseDAO.class);

    private CouchbaseTemplate couchbaseTemplate;
    private ObjectMapper objectMapper;

    @Autowired
    public CouchBaseDAO(CouchbaseTemplate couchbaseTemplate, ObjectMapper objectMapper) {
        this.couchbaseTemplate = couchbaseTemplate;
        this.objectMapper = objectMapper;
    }

    public List<Test> getTestData(String id) {
        Statement statement = select("test.*")
                .from(i("test"))
                .where(x("`value`").eq(s(id)))
                .limit(100);
        N1qlQueryResult result = couchbaseTemplate.getCouchbaseBucket()
                .query(N1qlQuery.simple(statement));

        return result.allRows().stream().map(item -> this.toModel(objectMapper, item, Test.class))
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    }
}
