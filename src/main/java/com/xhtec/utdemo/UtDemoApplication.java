package com.xhtec.utdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class UtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtDemoApplication.class, args);
	}
}
