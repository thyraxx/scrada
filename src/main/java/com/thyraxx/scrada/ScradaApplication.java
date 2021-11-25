package com.thyraxx.scrada;

import com.thyraxx.scrada.smashgg.configuration.ConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScradaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ScradaApplication.class, args);
	}
}
