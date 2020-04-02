package com.example.demo.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MetaUpdateEventPublisher {
    private final Logger logger = LoggerFactory.getLogger(MetaUpdateEvent.class);
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public MetaUpdateEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    public void publishEvent(String message) {
        logger.info(message);
        MetaUpdateEvent metaUpdateEvent = new MetaUpdateEvent(this, message);
        applicationEventPublisher.publishEvent(metaUpdateEvent);
    }
}
