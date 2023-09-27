package com.jeanpiress.breurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BrEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrEurekaServerApplication.class, args);
	}

}
