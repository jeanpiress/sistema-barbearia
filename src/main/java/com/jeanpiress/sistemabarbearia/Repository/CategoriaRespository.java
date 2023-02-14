package com.jeanpiress.sistemabarbearia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.sistemabarbearia.entities.Categoria;

public interface CategoriaRespository extends JpaRepository<Categoria, Long>{

}
