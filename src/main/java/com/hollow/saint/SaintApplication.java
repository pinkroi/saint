package com.hollow.saint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SaintApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaintApplication.class, args);
	}

}
