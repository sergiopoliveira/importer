package com.sergio.importer.bootstrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sergio.importer.parser.Parser;
import com.sergio.importer.sender.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.JmsException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Iterator;

/**
 * CommandLineRunner is Spring Boot specific
 * which allows running code on startup
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

    private Parser parser;
    private ObjectMapper mapper;
    private MessageSender messageSender;

    public Bootstrap(Parser parser, ObjectMapper mapper, MessageSender messageSender) {
        this.parser = parser;
        this.mapper = mapper;
        this.messageSender = messageSender;
    }

    @Override
    public void run(String... args) throws Exception {

     try {
         InputStream input = getClass().getResourceAsStream("/static/dataExample.csv");
         Iterator<Object> it = parser.parse(input);

         while (it.hasNext()) {

             // object to json
             String json = mapper.writeValueAsString(it.next());
             LOG.info(json);

             // submit to queue
             messageSender.send(json);
             LOG.info("Sent!");
         }

     } catch(JmsException e) {
         LOG.error("Unavailable ActiveMQ instance");
     }

    }
}
