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
import com.jeanpiress.sistemabarbearia.Repository.ProfissionalRespository;
import com.jeanpiress.sistemabarbearia.Repository.ServicoRespository;
import com.jeanpiress.sistemabarbearia.entities.Categoria;
import com.jeanpiress.sistemabarbearia.entities.Cliente;
import com.jeanpiress.sistemabarbearia.entities.Pedido;
import com.jeanpiress.sistemabarbearia.entities.Profissional;
import com.jeanpiress.sistemabarbearia.entities.Servico;

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
	
	@Autowired
	private ServicoRespository servicorepository;
	
	@Autowired
	private ProfissionalRespository profissionalrepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Produtos");
		Categoria cat2 = new Categoria(null, "Cabelo");
		Categoria cat3 = new Categoria(null, "Barba");
		Categoria cat4 = new Categoria(null, "Quimica");
		Categoria cat5 = new Categoria(null, "Combos");

		categoriarepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5));
		
		Servico s1 = new Servico(null, "Corte 40", 40.0, null, cat2, 50.0);
		Servico s2 = new Servico(null, "Barba 40", 40.0, null, cat3, 50.0);
		Servico s3 = new Servico(null, "Corte e barba 70", 70.0, null, cat5, 50.0);

		servicorepository.saveAll(Arrays.asList(s1, s2, s3));
		
		
		Profissional prof1 = new Profissional(null, "Jean Pires", Instant.parse("1991-11-13T08:14:20Z"), "(34)999708382" , "Rua Jose lelis Franca 1275",
				"Jean", "8836", 0.0, null);
		Profissional prof2 = new Profissional(null, "Victor Cruz", Instant.parse("1996-08-13T08:14:20Z"), "(34)999999999" , "Rua Quinto dos infernos 123",
				"Victor", "1234", 0.0, null);
		Profissional prof3 = new Profissional(null, "Marcos Mendes", Instant.parse("1992-01-15T08:14:20Z"), "(34)999999999" , "Rua Porteirinha 68",
				"Marcos", "1234", 1305.0, null);
		Profissional prof4 = new Profissional(null, "Julio Cesar", Instant.parse("2000-12-26T08:14:20Z"), "(34)999999999" , "Rua do Cruzeiro 3",
				"Xulim", "1234", 0.0, null);
		
		profissionalrepository.saveAll(Arrays.asList(prof1, prof2, prof3, prof4));
		
		Cliente c1 = new Cliente(null, "Jean", Instant.parse("1991-11-13T08:14:20Z"), "99999999", 30, 20, "gita");
		Cliente c2 = new Cliente(null, "Carol", Instant.parse("1990-08-21T08:14:20Z"), "99999999", 30, 30, "gita120");

		clienterepository.saveAll(Arrays.asList(c1, c2));

		Pedido p1 = new Pedido(null, Instant.parse("2022-02-14T08:14:20Z"), c1);
		Pedido p2 = new Pedido(null, Instant.parse("2022-02-14T08:12:30Z"), c2);

		pedidorepository.saveAll(Arrays.asList(p1, p2));

	}
}
