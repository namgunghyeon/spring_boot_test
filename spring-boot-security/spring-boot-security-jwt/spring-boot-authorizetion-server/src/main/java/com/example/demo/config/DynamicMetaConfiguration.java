package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicMetaConfiguration {
    private Map<String, KeyProps> keyMap;

    @PostConstruct
    public void init() {
        Map<String, KeyProps> keyMap = new HashMap<>();
        keyMap.put("admin5", new KeyProps("/users/me2", true));
        keyMap.put("admin6", new KeyProps("/users/me2", true));
        this.setKeyMap(keyMap);

    }

    public KeyProps getKeyProps(String key) {
        return keyMap.getOrDefault(key, null);
    }

    public void setKeyMap(Map<String, KeyProps> keyMap) {
        this.keyMap = keyMap;
    }
}
