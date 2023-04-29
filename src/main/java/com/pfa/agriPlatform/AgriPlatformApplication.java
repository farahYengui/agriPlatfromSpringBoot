package com.pfa.agriPlatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class AgriPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriPlatformApplication.class, args);
	}

}
