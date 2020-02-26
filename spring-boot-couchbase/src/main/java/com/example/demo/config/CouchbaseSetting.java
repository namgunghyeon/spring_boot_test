package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CouchbaseSetting {

    protected static final Logger logger = LoggerFactory.getLogger(CouchbaseSetting.class);

    private String hostName = "localhost";

    private String bucketName = "test";

    private String password = "123456";

    private String username = "test";

    public CouchbaseSetting() {
        logger.info("Loading Couchbase properties");
    }


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}