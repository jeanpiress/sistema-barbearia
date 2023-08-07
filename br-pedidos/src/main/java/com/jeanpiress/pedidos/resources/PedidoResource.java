package com.jeanpiress.pedidos.resources;

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

import com.jeanpiress.pedidos.entities.Pedido;
import com.jeanpiress.pedidos.entities.Produto;
import com.jeanpiress.pedidos.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;
	
	
	@GetMapping
	public ResponseEntity<List<Pedido>> getPedido(){
		List<Pedido> pedidos = service.buscar();
		return ResponseEntity.ok(pedidos);
	}
	
	@PostMapping
	public ResponseEntity<Pedido> salvar(@RequestBody Pedido obj){
		Pedido pedido = service.cadastrar(obj);
		return ResponseEntity.ok(pedido); 
	}
	
		
	@GetMapping(value = "/{id}")
	ResponseEntity<Optional<Pedido>> buscarPorId(@PathVariable Long id){
		Optional<Pedido> pedido = service.buscarPorId(id);
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Pedido> Alterar(@PathVariable Long id, @RequestBody Pedido obj){
		Pedido pedido = service.alterar(id, obj);
		return ResponseEntity.ok(pedido);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}/pruduto/{produtoId}")
	ResponseEntity<Pedido> adicionarProduto(@PathVariable Long id, @PathVariable Long produtoId){
		Pedido pedido = service.adicionarProduto(id, produtoId);
		return ResponseEntity.ok(pedido);
	}
	
	
	
}
