package com.ftn.sbnz.model;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ModelApplication implements ApplicationRunner {

	public static void main(String[] args) {
//		SpringApplication.run(ModelApplication.class, args);
		System.out.println("Hello from model!");
		ApplicationContext ctx = SpringApplication.run(ModelApplication.class, args);

	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
