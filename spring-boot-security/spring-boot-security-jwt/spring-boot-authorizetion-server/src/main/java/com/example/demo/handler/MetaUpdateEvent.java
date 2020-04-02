package com.example.demo.handler;

import org.springframework.context.ApplicationEvent;

public class MetaUpdateEvent extends ApplicationEvent {
    private String message;

    public MetaUpdateEvent(Object source, String message) {
        super(source);
    }

    public String getMessage() {
        return message;
    }
}
