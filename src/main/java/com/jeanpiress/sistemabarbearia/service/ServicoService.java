package com.jeanpiress.sistemabarbearia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.sistemabarbearia.Repository.ServicoRespository;
import com.jeanpiress.sistemabarbearia.entities.Servico;

@Service
public class ServicoService {

	@Autowired
	private ServicoRespository repository;


   public List<Servico> buscar(){
	  return repository.findAll();
   }

   public Servico buscarPorId(Long id) {
	  Optional<Servico> obj = repository.findById(id);
	  return obj.get();
   }


}
