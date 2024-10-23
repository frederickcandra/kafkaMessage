package com.event.kafkamessage.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducerService {
    private static final String TOPIC = "user-events";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLoginEvent(String username) {
        String eventMessage = "User " + username + " has logged in.";
        kafkaTemplate.send(TOPIC, eventMessage);
    }

    public void sendRegisterEvent(String username) {
        String eventMessage = "User " + username + " has been created";
        kafkaTemplate.send(TOPIC, eventMessage);
    }
}
