package com.example.demo.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.example.demo.utils.N1QL;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Test implements CouchbaseModel<Test> {
    public static Logger logger = LoggerFactory.getLogger(N1QL.class);

    public Test(String id, String value, String field) {
        this.id = id;
        this.value = value;
        this.field = field;
    }

    public Test() {
    }

    @Id
    private String id;

    @Field
    private String value;

    @Field
    private String field;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public Test toModel(JsonNode jsonNode, ObjectMapper objectMapper) throws IOException {
        return objectMapper.treeToValue(jsonNode, Test.class);
    }
}
