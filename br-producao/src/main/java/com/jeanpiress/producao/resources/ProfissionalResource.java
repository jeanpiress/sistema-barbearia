package com.jeanpiress.producao.resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.producao.entities.Profissional;
import com.jeanpiress.producao.services.ProfissionalService;

@RestController
@RequestMapping(value= "/profissionais")
public class ProfissionalResource {

	@Autowired
	ProfissionalService service;
	
	
	@GetMapping
	ResponseEntity<List<Profissional>> buscar(){
		List<Profissional> profissionais = service.buscar();
		return ResponseEntity.ok(profissionais);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Profissional>> buscarPorId(@PathVariable Long id){
		Optional<Profissional> profissional = service.buscarPorId(id);
		return ResponseEntity.ok(profissional);
	}
	
	@PostMapping
	ResponseEntity<Profissional> cadastrar(@RequestBody Profissional obj){
		Profissional profissional = service.cadastrar(obj);
		return ResponseEntity.ok(profissional);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Profissional> Alterar(@PathVariable Long id, @RequestBody Profissional obj){
		Profissional profissional = service.alterar(id, obj);
		return ResponseEntity.ok(profissional);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
