package com.jeanpiress.sistemabarbearia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.sistemabarbearia.entities.Profissional;
import com.jeanpiress.sistemabarbearia.service.ProfissionalService;

@RestController
@RequestMapping(value = "/profissionais")
public class ProfissionalResource {

	@Autowired
	private ProfissionalService service;
	
	@GetMapping
	public ResponseEntity<List<Profissional>> buscar(){
	List<Profissional> profissional = service.buscar();
	return ResponseEntity.ok().body(profissional);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Profissional> buscarPorId(@PathVariable Long id){
		Profissional profissional = service.buscarPorId(id);
		return ResponseEntity.ok().body(profissional);
	}
}
