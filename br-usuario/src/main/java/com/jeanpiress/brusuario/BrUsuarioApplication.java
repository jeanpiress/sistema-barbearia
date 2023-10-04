package com.jeanpiress.brusuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BrUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrUsuarioApplication.class, args);
	}

}
