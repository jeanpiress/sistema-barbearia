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

import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.services.BoletoService;

@RestController
@RequestMapping(value= "/boletos")
public class BoletoResource {

	@Autowired
	BoletoService service;
	
	
	@GetMapping
	ResponseEntity<List<Boleto>> buscar(){
		List<Boleto> boletos = service.buscar();
		return ResponseEntity.ok(boletos);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Boleto>> buscarPorId(@PathVariable Long id){
		Optional<Boleto> boleto = service.buscarPorId(id);
		return ResponseEntity.ok(boleto);
	}
	
	@PostMapping
	ResponseEntity<Boleto> cadastrar(@RequestBody Boleto obj){
		Boleto boleto = service.cadastrar(obj);
		return ResponseEntity.ok(boleto);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Boleto> Alterar(@PathVariable Long id, @RequestBody Boleto obj){
		Boleto boleto = service.alterar(id, obj);
		return ResponseEntity.ok(boleto);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
