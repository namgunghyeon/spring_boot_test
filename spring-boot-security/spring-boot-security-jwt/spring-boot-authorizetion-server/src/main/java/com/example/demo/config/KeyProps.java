package com.example.demo.config;

public class KeyProps {
    private String uri;
    private boolean isActivate;

    public KeyProps(String uri, boolean isActivate) {
        this.uri = uri;
        this.isActivate = isActivate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public boolean getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(boolean isActivate) {
        this.isActivate = isActivate;
    }
}
