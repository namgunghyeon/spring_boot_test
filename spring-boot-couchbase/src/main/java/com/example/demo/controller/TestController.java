package com.example.demo.controller;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.example.demo.config.LocalCouchbaseCluster;
import com.example.demo.model.Test;
import com.example.demo.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private LocalCouchbaseCluster localCouchbaseCluster;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(path = "/test")
    public List<Test> test() {
        return testService.getTest("test");
    }

    @GetMapping(path = "/test2")
    public Map<String, Object> test2() {
        Bucket bucket = localCouchbaseCluster.getBucket("test");
        JsonDocument jsonDocument = bucket.get("1");
        JsonObject data = jsonDocument.content();

        Map<String, Object> resultMap = new HashMap<>();
        data.getNames().forEach(name -> {
            resultMap.put(name, data.get(name));
        });

        Map<String, Object> map2 = new HashMap<>();
        map2.put("inner", resultMap);

        Map<String, Object> map = new HashMap<>();
        map.put("data", Arrays.asList("test", "test2"));
        map.put("data2", new HashMap<>());
        map.put("data3", map2);

        return map;
    }
}
