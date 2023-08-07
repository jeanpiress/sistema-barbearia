package com.jeanpiress.pedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.pedidos.entities.Cliente;
import com.jeanpiress.pedidos.services.ClienteService;

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
	
	@PostMapping
	public ResponseEntity<Cliente> salvar(@RequestBody Cliente obj){
		Cliente cliente = service.adicionar(obj);
		return ResponseEntity.ok(cliente); 
	}
}
