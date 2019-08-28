package com.sergio.importer.sender;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderImpl implements MessageSender {

    private static final String AGGREGATE_REQUEST_QUEUE = "aggregate-request";

    private final JmsTemplate jmsTemplate;

    public MessageSenderImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void send(String jsonString) {
        jmsTemplate.convertAndSend(AGGREGATE_REQUEST_QUEUE, (jsonString));
    }
}
