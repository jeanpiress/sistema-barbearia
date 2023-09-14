package com.jeanpiress.producao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jeanpiress.producao.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "select c from Cliente c where upper(c.nome) like %?1%")
	List<Cliente> buscarPorNome(String nome);
	
	
	
}
