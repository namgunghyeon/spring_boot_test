package com.example.demo.handler;

import com.example.demo.config.DynamicMetaConfiguration;
import com.example.demo.config.KeyProps;
import com.example.demo.model.Service;
import com.example.demo.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationEventListener {
    private Logger logger = LoggerFactory.getLogger(ApplicationEventListener.class);
    private final ServiceRepository serviceRepository;
    private final DynamicMetaConfiguration dynamicMetaConfiguration;

    @Autowired
    public ApplicationEventListener(ServiceRepository serviceRepository, DynamicMetaConfiguration dynamicMetaConfiguration) {
        this.serviceRepository = serviceRepository;
        this.dynamicMetaConfiguration = dynamicMetaConfiguration;
    }

    @EventListener(classes = { ContextRefreshedEvent.class, MetaUpdateEvent.class })
    public void eventHandler() {
        List<Service> services = serviceRepository.findAll();
        logger.info("updated meta!!");
        dynamicMetaConfiguration.setKeyMap(buildKeyMap(services));
    }

    private Map<String, KeyProps> buildKeyMap(List<Service> services) {
        Map<String, KeyProps> keyMap = new HashMap<>();
        services.forEach(item -> {
            item.getAccessKeys().forEach(key -> {
                keyMap.put(key.getKey(), new KeyProps(item.getAccessUri(), item.getIsActivated() == 1));
            });
        });

        return keyMap;
    }
}
