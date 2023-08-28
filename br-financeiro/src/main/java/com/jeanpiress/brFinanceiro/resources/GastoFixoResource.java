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

import com.jeanpiress.brFinanceiro.entities.GastoFixo;
import com.jeanpiress.brFinanceiro.services.GastoFixoService;

@RestController
@RequestMapping(value= "/gastoFixos")
public class GastoFixoResource {

	@Autowired
	GastoFixoService service;
	
	
	@GetMapping
	ResponseEntity<List<GastoFixo>> buscar(){
		List<GastoFixo> profissionais = service.buscar();
		return ResponseEntity.ok(profissionais);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<GastoFixo>> buscarPorId(@PathVariable Long id){
		Optional<GastoFixo> gastoFixo = service.buscarPorId(id);
		return ResponseEntity.ok(gastoFixo);
	}
	
	@PostMapping
	ResponseEntity<GastoFixo> cadastrar(@RequestBody GastoFixo obj){
		GastoFixo gastoFixo = service.cadastrar(obj);
		return ResponseEntity.ok(gastoFixo);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<GastoFixo> Alterar(@PathVariable Long id, @RequestBody GastoFixo obj){
		GastoFixo gastoFixo = service.alterar(id, obj);
		return ResponseEntity.ok(gastoFixo);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
