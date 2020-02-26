package com.example.demo.utils;

import com.couchbase.client.java.query.N1qlQueryRow;
import com.example.demo.model.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class N1QL {
    public static Logger logger = LoggerFactory.getLogger(N1QL.class);

    public static JsonNode extractJsonResult(N1qlQueryRow result, ObjectMapper objectMapper) {
        try {
            return objectMapper.readTree(result.value().toString());
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T toModel(JsonNode jsonNode, ObjectMapper objectMapper, Class<T> claz) throws IOException {
        return objectMapper.treeToValue(jsonNode, claz);
    }
}
