package com.jeanpiress.produtos.resources;

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

import com.jeanpiress.produtos.entities.Produto;
import com.jeanpiress.produtos.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	ProdutoService service;
	
	
	@GetMapping
	ResponseEntity<List<Produto>> buscar(){
		List<Produto> produtos = service.buscar();
		return ResponseEntity.ok(produtos);
	}
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Produto>> buscarPorId(@PathVariable Long id){
		Optional<Produto> profissional = service.buscarPorId(id);
		return ResponseEntity.ok(profissional);
	}
	
	@PostMapping
	ResponseEntity<Produto> cadastrar(@RequestBody Produto obj){
		Produto profissional = service.cadastrar(obj);
		return ResponseEntity.ok(profissional);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Produto> Alterar(@PathVariable Long id, @RequestBody Produto obj){
		Produto profissional = service.alterar(id, obj);
		return ResponseEntity.ok(profissional);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
