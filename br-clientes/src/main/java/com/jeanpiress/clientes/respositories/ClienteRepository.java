package com.jeanpiress.clientes.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanpiress.clientes.entites.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
