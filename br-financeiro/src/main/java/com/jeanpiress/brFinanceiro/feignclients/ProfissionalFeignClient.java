package com.jeanpiress.brFinanceiro.feignclients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jeanpiress.brFinanceiro.entities.Profissional;

@Component
@FeignClient(name = "profissionaisFeing", url = "localhost:8001", path = "/profissionais")
public interface ProfissionalFeignClient {

	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Profissional>> buscarPorId(@PathVariable Long id);
		
	
}
