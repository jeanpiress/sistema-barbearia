package com.jeanpiress.pedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.pedidos.entities.Produto;
import com.jeanpiress.pedidos.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Produto>> getProduto(){
		List<Produto> produtos = service.buscar();
		return ResponseEntity.ok(produtos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> produtoPorId(@PathVariable Long id){
		Produto produto = service.buscarPorId(id);
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto obj){
		Produto produto = service.adicionar(obj);
		return ResponseEntity.ok(produto); 
	}
}
