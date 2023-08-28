package com.jeanpiress.brFinanceiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entities.Profissional;
import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.feignclients.PedidoFeignClient;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;

@Service
public class SalarioService {
	
	@Autowired
	private ProfissionalFeignClient profissionalFeignClient;
	
	@Autowired
	private PedidoFeignClient pedidoFeignClient;
	

	public Salario getSalario(Long profissionalId, String inicio, String fim) {
		Profissional profissional = profissionalFeignClient.buscarPorId(profissionalId).getBody().get();
		
		Double comissao = pedidoFeignClient.verificarComissaoPorPeriodo(profissionalId, inicio, fim).getBody();
		
		return new Salario(profissional.getNome(), profissional.getSalarioFixo(), comissao);
	}
}
