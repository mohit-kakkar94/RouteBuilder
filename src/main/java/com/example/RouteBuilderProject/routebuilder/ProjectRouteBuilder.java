package com.example.RouteBuilderProject.routebuilder;
import com.example.RouteBuilderProject.entity.User;
import com.example.RouteBuilderProject.processor.UserProcessor;
import com.example.RouteBuilderProject.repository.UserRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("amqp:user-queue")
                .log("Message Recieved")
                .log("Received message from user-queue: ${body}")
                .onException(Exception.class)
                .log("Error")
                .end()
                .process(new UserProcessor()).log("Process complete!");

//        from("timer:query-timer?period=5000")
//                .log("Querying users from MongoDB")
//                .process(exchange -> {
//                    // Query users from MongoDB
//                    Iterable<User> users = userRepository.findAll();
//                    StringBuilder result = new StringBuilder();
//                    users.forEach(user -> result.append(user.getName()).append("\n"));
//                    exchange.getMessage().setBody(result.toString());
//                })
//                .to("activemq:queue:query-result-queue");
    }

}
