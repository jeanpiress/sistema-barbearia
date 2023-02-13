package com.jeanpiress.sistemabarbearia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.sistemabarbearia.entities.Cliente;

public interface ClienteRespository extends JpaRepository<Cliente, Long>{

}
