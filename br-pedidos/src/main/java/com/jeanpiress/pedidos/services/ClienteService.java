package com.jeanpiress.pedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.pedidos.entities.Cliente;
import com.jeanpiress.pedidos.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	

	public List<Cliente> buscar() {
		List<Cliente> pedidos = repository.findAll();
		return pedidos;
	}

	public Cliente adicionar(Cliente pedido) {
		return repository.save(pedido);
	}

}
