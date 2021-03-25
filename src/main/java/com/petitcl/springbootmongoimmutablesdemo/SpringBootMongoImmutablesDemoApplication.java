package com.petitcl.springbootmongoimmutablesdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.tools.agent.ReactorDebugAgent;

@SpringBootApplication
public class SpringBootMongoImmutablesDemoApplication {

	public static void main(String[] args) {
		ReactorDebugAgent.init();
		SpringApplication.run(SpringBootMongoImmutablesDemoApplication.class, args);
	}

}
