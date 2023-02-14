package com.jeanpiress.sistemabarbearia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.sistemabarbearia.Repository.PedidoRespository;
import com.jeanpiress.sistemabarbearia.entities.Pedido;

@Service
public class PedidoService {

	@Autowired
	private PedidoRespository repository;


   public List<Pedido> buscar(){
	  return repository.findAll();
   }

   public Pedido buscarPorId(Long id) {
	  Optional<Pedido> obj = repository.findById(id);
	  return obj.get();
   }


}
