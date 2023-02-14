package com.jeanpiress.sistemabarbearia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.sistemabarbearia.Repository.CategoriaRespository;
import com.jeanpiress.sistemabarbearia.entities.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRespository repository;


   public List<Categoria> buscar(){
	  return repository.findAll();
   }

   public Categoria buscarPorId(Long id) {
	  Optional<Categoria> obj = repository.findById(id);
	  return obj.get();
   }


}
