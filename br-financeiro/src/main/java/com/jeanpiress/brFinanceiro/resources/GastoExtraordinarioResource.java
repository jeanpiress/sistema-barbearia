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

import com.jeanpiress.brFinanceiro.entities.GastoExtraordinario;
import com.jeanpiress.brFinanceiro.services.GastoExtraordinarioService;

@RestController
@RequestMapping(value= "/gastosExtraordinarios")
public class GastoExtraordinarioResource {

	@Autowired
	GastoExtraordinarioService service;
	
	
	@GetMapping
	ResponseEntity<List<GastoExtraordinario>> buscar(){
		List<GastoExtraordinario> gastosExtraordinarios = service.buscar();
		return ResponseEntity.ok(gastosExtraordinarios);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<GastoExtraordinario>> buscarPorId(@PathVariable Long id){
		Optional<GastoExtraordinario> gastoExtraordinario = service.buscarPorId(id);
		return ResponseEntity.ok(gastoExtraordinario);
	}
	
	@PostMapping
	ResponseEntity<GastoExtraordinario> cadastrar(@RequestBody GastoExtraordinario obj){
		GastoExtraordinario gastoExtraordinario = service.cadastrar(obj);
		return ResponseEntity.ok(gastoExtraordinario);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<GastoExtraordinario> Alterar(@PathVariable Long id, @RequestBody GastoExtraordinario obj){
		GastoExtraordinario gastoExtraordinario = service.alterar(id, obj);
		return ResponseEntity.ok(gastoExtraordinario);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
