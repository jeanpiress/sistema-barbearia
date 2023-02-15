package com.jeanpiress.sistemabarbearia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.sistemabarbearia.entities.Servico;
import com.jeanpiress.sistemabarbearia.service.ServicoService;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoResource {

	@Autowired
	private ServicoService service;
	
	@GetMapping
	public ResponseEntity<List<Servico>> buscar(){
	List<Servico> servico = service.buscar();
	return ResponseEntity.ok().body(servico);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Servico> buscarPorId(@PathVariable Long id){
		Servico servico = service.buscarPorId(id);
		return ResponseEntity.ok().body(servico);
	}
}
