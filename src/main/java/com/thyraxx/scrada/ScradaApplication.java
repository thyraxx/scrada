package com.thyraxx.scrada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScradaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScradaApplication.class, args);
	}
}
