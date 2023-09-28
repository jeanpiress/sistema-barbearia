package com.jeanpiress.brFinanceiro.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.brFinanceiro.entities.Salario;
import com.jeanpiress.brFinanceiro.services.RelatorioService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioResource {

	@Autowired
	RelatorioService service;
	
	@GetMapping(value = "/profissional/{id}/inicio/{inicio}/fim/{fim}")
	public ResponseEntity<Salario> salarioProfissionalPeriodo(@PathVariable Long id, @PathVariable String inicio, @PathVariable String fim){
		Salario salario = service.salarioFuncionarioPorPeriodo(id, inicio, fim);
		return ResponseEntity.ok(salario);
	}
	
	@HystrixCommand(fallbackMethod = "salarioProfissionalMesAlternativo")
	@GetMapping(value = "/profissional/{id}/ano/{ano}/mes/{mes}")
	public ResponseEntity<Salario> salarioProfissionalMes(@PathVariable Long id, @PathVariable int ano, @PathVariable int mes){
		Salario salario = service.salarioFuncionarioMes(id, ano, mes);
		return ResponseEntity.ok(salario);
	}
	
	
	public ResponseEntity<Salario> salarioProfissionalMesAlternativo(Long id, int ano, int mes){
		Salario salario = new Salario("Erro", 0.0, 0.0);
		return ResponseEntity.ok(salario);
	}
}
