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

import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.services.CategoriaProdutoService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaProdutoResource {

	@Autowired
	private CategoriaProdutoService service;
	
	
	@GetMapping
	public ResponseEntity<List<CategoriaProduto>> getCategoriaProduto(){
		List<CategoriaProduto> categorias = service.buscar();
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<CategoriaProduto>> buscarPorId(@PathVariable Long id){
		Optional<CategoriaProduto> categoria = service.buscarPorId(id);
		return ResponseEntity.ok(categoria);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaProduto> salvar(@RequestBody CategoriaProduto obj){
		CategoriaProduto categoria = service.cadastrar(obj);
		return ResponseEntity.ok(categoria); 
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<CategoriaProduto> Alterar(@PathVariable Long id, @RequestBody CategoriaProduto obj){
		CategoriaProduto categoria = service.alterar(id, obj);
		return ResponseEntity.ok(categoria);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
