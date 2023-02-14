package com.jeanpiress.sistemabarbearia.config;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeanpiress.sistemabarbearia.Repository.CategoriaRespository;
import com.jeanpiress.sistemabarbearia.Repository.ClienteRespository;
import com.jeanpiress.sistemabarbearia.Repository.PedidoRespository;
import com.jeanpiress.sistemabarbearia.entities.Categoria;
import com.jeanpiress.sistemabarbearia.entities.Cliente;
import com.jeanpiress.sistemabarbearia.entities.Pedido;

@Configuration
//@Profile("test")
public class TesteConfig implements CommandLineRunner {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private ClienteRespository clienterepository;

	@Autowired
	private PedidoRespository pedidorepository;

	@Autowired
	private CategoriaRespository categoriarepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Produtos");
		Categoria cat2 = new Categoria(null, "Cabelo");
		Categoria cat3 = new Categoria(null, "Barba");
		Categoria cat4 = new Categoria(null, "Quimica");

		categoriarepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

		Cliente c1 = new Cliente(null, "Jean", Instant.parse("1991-11-13T08:14:20Z"), "99999999", 30, 20, "gita");
		Cliente c2 = new Cliente(null, "Carol", Instant.parse("1990-08-21T08:14:20Z"), "99999999", 30, 30, "gita120");

		clienterepository.saveAll(Arrays.asList(c1, c2));

		Pedido p1 = new Pedido(null, Instant.parse("2022-02-14T08:14:20Z"), c1);
		Pedido p2 = new Pedido(null, Instant.parse("2022-02-14T08:12:30Z"), c2);

		pedidorepository.saveAll(Arrays.asList(p1, p2));

	}
}
