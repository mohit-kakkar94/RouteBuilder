package com.example.RouteBuilderProject.Message;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

//@Component
//public class MessageReceiver implements MessageListener {
////
////    private CamelContext camelContext;
////
////    @JmsListener(destination = "user-queue")
////    public void receiveMessage(String message) {
////        // Process the received message using Camel route logic
////        System.out.println("Mohit");
////        System.out.println("Message Sent");
////    }
////
////    @Override
////    public void onMessage(Message message) {
////        try {
////            System.out.println("bcuwhcuhiecniew");
////            // Create a new Camel Exchange
////            Exchange exchange = camelContext.getEndpoint("direct:start").createExchange();
////
////            // Set the JMS message as the body of the Camel Exchange
////            exchange.getIn().setBody(message);
////
////            // Send the Camel Exchange to the RouteBuilder
////            ProducerTemplate producer = camelContext.createProducerTemplate();
////            producer.send("direct:start", exchange);
////        } catch (Exception e) {
////            // Handle JMS exception
////        }
////    }
//}
