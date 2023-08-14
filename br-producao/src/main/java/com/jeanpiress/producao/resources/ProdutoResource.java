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

import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.services.ProdutoService;

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
	public ResponseEntity<Optional<Produto>> produtoPorId(@PathVariable Long id){
		Optional<Produto> produto = service.buscarPorId(id);
		return ResponseEntity.ok(produto);
	}
	
	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody Produto obj){
		Produto produto = service.adicionar(obj);
		return ResponseEntity.ok(produto); 
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
	
	@PutMapping(value = "/{id}/categoria/{categoriaId}")
	ResponseEntity<Produto> adicionarProduto(@PathVariable Long id, @PathVariable Long categoriaId){
		Produto produto = service.adicionarCategoria(id, categoriaId);
		return ResponseEntity.ok(produto);
	}
}
