package com.example.RouteBuilderProject;

import com.example.RouteBuilderProject.repository.UserRepository;
import com.example.RouteBuilderProject.routebuilder.ProjectRouteBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class RouteBuilderProjectApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) throws Exception {

		CamelContext camelContext = new DefaultCamelContext();
		// Add your RouteBuilder
		camelContext.addRoutes(new ProjectRouteBuilder());
		// Start the CamelContext
		camelContext.start();
		// Keep the application running
		Thread.sleep(5000);
		// Stop the CamelContext
		camelContext.stop();

		SpringApplication.run(RouteBuilderProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
	}
}
