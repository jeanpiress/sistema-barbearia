package com.jeanpiress.sistemabarbearia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.sistemabarbearia.Repository.ProfissionalRespository;
import com.jeanpiress.sistemabarbearia.entities.Profissional;

@Service
public class ProfissionalService {

	@Autowired
	private ProfissionalRespository repository;


   public List<Profissional> buscar(){
	  return repository.findAll();
   }

   public Profissional buscarPorId(Long id) {
	  Optional<Profissional> obj = repository.findById(id);
	  return obj.get();
   }


}
