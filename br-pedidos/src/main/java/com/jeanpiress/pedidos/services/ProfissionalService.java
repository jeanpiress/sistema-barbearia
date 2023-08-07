package com.jeanpiress.pedidos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.pedidos.entities.Profissional;
import com.jeanpiress.pedidos.repository.ProfissionalRepository;

@Service
public class ProfissionalService {

	@Autowired
	ProfissionalRepository repository;

	

	public List<Profissional> buscar() {
		List<Profissional> pedidos = repository.findAll();
		return pedidos;
	}

	public Profissional adicionar(Profissional pedido) {
		return repository.save(pedido);
	}

}
