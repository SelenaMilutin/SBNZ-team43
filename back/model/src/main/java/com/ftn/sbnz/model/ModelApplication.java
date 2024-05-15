package com.ftn.sbnz.model;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;


public class ModelApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ModelApplication.class, args);
		System.out.println("Hello from model!");
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {

	}
}
