package com.jeanpiress.brFinanceiro.resources;

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

import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.services.CredorService;

@RestController
@RequestMapping(value= "/credores")
public class CredorResource {

	@Autowired
	CredorService service;
	
	
	@GetMapping
	ResponseEntity<List<Credor>> buscar(){
		List<Credor> credores = service.buscar();
		return ResponseEntity.ok(credores);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Credor>> buscarPorId(@PathVariable Long id){
		Optional<Credor> credor = service.buscarPorId(id);
		return ResponseEntity.ok(credor);
	}
	
	@PostMapping
	ResponseEntity<Credor> cadastrar(@RequestBody Credor obj){
		Credor credor = service.cadastrar(obj);
		return ResponseEntity.ok(credor);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Credor> Alterar(@PathVariable Long id, @RequestBody Credor obj){
		Credor credor = service.alterar(id, obj);
		return ResponseEntity.ok(credor);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
