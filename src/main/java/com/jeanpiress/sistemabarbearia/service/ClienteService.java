package com.jeanpiress.sistemabarbearia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.sistemabarbearia.Repository.ClienteRespository;
import com.jeanpiress.sistemabarbearia.entities.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRespository repository;


   public List<Cliente> buscar(){
	  return repository.findAll();
   }

   public Cliente buscarPorId(Long id) {
	  Optional<Cliente> obj = repository.findById(id);
	  return obj.get();
   }


}
