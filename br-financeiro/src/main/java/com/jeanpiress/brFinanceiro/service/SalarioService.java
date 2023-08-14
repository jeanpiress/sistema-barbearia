package com.jeanpiress.brFinanceiro.service;

import org.springframework.stereotype.Service;

import com.jeanpiress.brFinanceiro.entity.Salario;

@Service
public class SalarioService {

	public Salario getSalario(Long profissionalId) {
		return new Salario("Jean", 1000.0, 4000.0);
	}
}
