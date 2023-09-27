package com.jeanpiress.brFinanceiro.feignclients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jeanpiress.brFinanceiro.entities.Cliente;

@Component
@FeignClient(name = "br-producao", path = "/clientes")
public interface ClienteFeignClient {

	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Cliente>> buscarPorId(@PathVariable Long id);
		
	@GetMapping
	ResponseEntity<List<Cliente>> buscar();
}
