package com.jeanpiress.sistemabarbearia.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeanpiress.sistemabarbearia.Repository.ClienteRespository;
import com.jeanpiress.sistemabarbearia.entities.Cliente;

@Configuration
//@Profile("test")
public class TesteConfig implements CommandLineRunner{

	@Autowired
	private ClienteRespository repository;

	@Override
	public void run(String... args) throws Exception {
		
		Cliente x1 = new Cliente(null, "Jean", null, "99999999", 30, null, "gita");
		Cliente x2 = new Cliente(null, "Carol", null, "99999999", 30, null, "gita120");
		
		repository.saveAll(Arrays.asList(x1, x2));
	}
}
