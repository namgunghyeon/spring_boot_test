package com.example.demo.dao;

import com.couchbase.client.java.query.N1qlQueryRow;
import com.example.demo.model.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class DAO {
    final private Logger logger = LoggerFactory.getLogger(CouchBaseDAO.class);

    protected JsonNode extractJsonResult(ObjectMapper objectMapper, N1qlQueryRow result) {
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
}
