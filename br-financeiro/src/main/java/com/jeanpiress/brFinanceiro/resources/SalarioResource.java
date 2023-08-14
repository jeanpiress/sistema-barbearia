package com.jeanpiress.brFinanceiro.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.brFinanceiro.entity.Salario;
import com.jeanpiress.brFinanceiro.service.SalarioService;


@RestController
@RequestMapping(value = "/salarios")
public class SalarioResource {

	@Autowired
	SalarioService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Salario> salario(@PathVariable Long id){
		Salario salario = service.getSalario(id);
		return ResponseEntity.ok(salario);
	}
}
