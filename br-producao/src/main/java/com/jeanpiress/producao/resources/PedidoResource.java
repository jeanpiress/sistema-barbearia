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

import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.services.PedidoService;

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
	
	@PutMapping(value = "/{id}/produto/{produtoId}/add")
	ResponseEntity<Pedido> adicionarProduto(@PathVariable Long id, @PathVariable Long produtoId){
		Pedido pedido = service.adicionarProduto(id, produtoId);
		return ResponseEntity.ok(pedido);
	}
	
	@PutMapping(value = "/{id}/produto/{produtoId}/remove")
	ResponseEntity<Pedido> removerProduto(@PathVariable Long id, @PathVariable Long produtoId){
		Pedido pedido = service.removerProduto(id, produtoId);
		return ResponseEntity.ok(pedido);
	}
	
	@GetMapping(value = "/{id}/comissao")
	ResponseEntity<Double> verificarComissao(@PathVariable Long id){
		Double comissao = service.comissaoPagaPorId(id);
		return ResponseEntity.ok(comissao);
	}
	
	@GetMapping(value = "profissional/{profissionalId}/ano/{ano}/mes/{mes}/comissao")
	ResponseEntity<Double> verificarComissaoProfissionalMes(@PathVariable Long profissionalId, @PathVariable int ano, @PathVariable int mes){
		Double comissao = service.comissaoPagaProfissionalMes(profissionalId, ano, mes);
		return ResponseEntity.ok(comissao);
	}
	
	@GetMapping(value = "profissional/{profissionalId}/inicio/{inicio}/fim/{fim}/comissao")
	ResponseEntity<Double> verificarComissaoPorPeriodo(@PathVariable Long profissionalId, @PathVariable String inicio, @PathVariable String fim){
		Double comissao = service.comissaoPagaPorPeriodo(profissionalId, inicio, fim);
		return ResponseEntity.ok(comissao);
	}
	
	@PutMapping(value = "/{id}/forma/{forma}/pagar")
	ResponseEntity<Pedido> adicionarProduto(@PathVariable Long id, @PathVariable int forma){
		Pedido pedido = service.pagamentoPedido(id, forma);
		return ResponseEntity.ok(pedido);
	}
	
	
	
}
