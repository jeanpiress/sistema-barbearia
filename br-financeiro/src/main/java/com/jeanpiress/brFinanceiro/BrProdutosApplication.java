package com.jeanpiress.brFinanceiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BrProdutosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrProdutosApplication.class, args);
	}

}
