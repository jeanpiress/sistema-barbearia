package com.jeanpiress.producao.resources;

import java.util.List;

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

import com.jeanpiress.producao.entities.Cliente;
import com.jeanpiress.producao.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getCliente(){
		List<Cliente> clientes = service.buscar();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
		Cliente cliente = service.buscarPorId(id).get();
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente obj){
		Cliente cliente = service.cadastrar(obj);
		return ResponseEntity.ok(cliente); 
	}
	
	@PutMapping(value = "/{id}")
	ResponseEntity<Cliente> Alterar(@PathVariable Long id, @RequestBody Cliente obj){
		Cliente cliente = service.alterar(id, obj);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	ResponseEntity<Void> deletar (@PathVariable Long id){
		service.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
