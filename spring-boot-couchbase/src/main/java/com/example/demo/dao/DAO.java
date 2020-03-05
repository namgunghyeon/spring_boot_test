package com.example.demo.dao;

import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.couchbase.client.java.query.Statement;
import com.example.demo.model.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import java.io.IOException;

public abstract class DAO {
    final private Logger logger = LoggerFactory.getLogger(CouchBaseDAO.class);
    private CouchbaseTemplate couchbaseTemplate;

    public DAO(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    public N1qlQueryResult execute(Statement st) {
        return couchbaseTemplate.getCouchbaseBucket()
                .query(N1qlQuery.simple(st));
    }

    private JsonNode extractJsonResult(ObjectMapper objectMapper, N1qlQueryRow result) {
        try {
            return objectMapper.readTree(result.value().toString());
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    protected <T> T toModel( ObjectMapper objectMapper, N1qlQueryRow result, Class<T> claz) {
        try {
            JsonNode jsonNode = this.extractJsonResult(objectMapper, result);
            return objectMapper.treeToValue(jsonNode, claz);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    public void setTemplate(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }
}
