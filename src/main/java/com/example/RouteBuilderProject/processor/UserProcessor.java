package com.example.RouteBuilderProject.processor;

import com.example.RouteBuilderProject.entity.User;
import com.example.RouteBuilderProject.repository.UserRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

public class UserProcessor implements Processor {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        // Save the received message to MongoDB
        User user = new User();
        user.setName(exchange.getIn().getBody(String.class));
        userRepository.save(user);
    }
}
