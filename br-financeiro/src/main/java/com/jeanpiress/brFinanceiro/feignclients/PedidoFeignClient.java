package com.jeanpiress.brFinanceiro.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jeanpiress.brFinanceiro.entities.Pedido;

@Component
@FeignClient(name = "pedidoFeign", url = "localhost:8001", path = "/pedidos")
public interface PedidoFeignClient {

	@GetMapping(value = "profissional/{profissionalId}/inicio/{inicio}/fim/{fim}/comissao")
	ResponseEntity<Double> verificarComissaoPorPeriodo(@PathVariable Long profissionalId, @PathVariable String inicio, @PathVariable String fim);
		
	@GetMapping
	public ResponseEntity<List<Pedido>> getPedido();
	
	@GetMapping(value = "profissional/{profissionalId}/ano/{ano}/mes/{mes}/comissao")
	ResponseEntity<Double> verificarComissaoProfissionalMes(@PathVariable Long profissionalId, @PathVariable int ano, @PathVariable int mes);
}
