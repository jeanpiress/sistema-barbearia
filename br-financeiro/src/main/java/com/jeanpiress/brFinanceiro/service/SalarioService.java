package com.jeanpiress.brFinanceiro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entity.Profissional;
import com.jeanpiress.brFinanceiro.entity.Salario;
import com.jeanpiress.brFinanceiro.feignclients.ProfissionalFeignClient;

@Service
public class SalarioService {
	
	@Autowired
	private ProfissionalFeignClient profissionalFeignClient;

	public Salario getSalario(Long profissionalId) {
		Profissional profissional = profissionalFeignClient.buscarPorId(profissionalId).getBody().get();
		
		
		return new Salario(profissional.getNome(), profissional.getSalarioFixo(), null);
	}
}
