package com.jeanpiress.brFinanceiro.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "pedidoFeign", url = "localhost:8001", path = "/pedidos")
public interface PedidoFeignClient {

	@GetMapping(value = "profissional/{profissionalId}/inicio/{inicio}/fim/{fim}/comissao")
	ResponseEntity<Double> verificarComissaoPorPeriodo(@PathVariable Long profissionalId, @PathVariable String inicio, @PathVariable String fim);
		
	
}
