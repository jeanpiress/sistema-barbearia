package com.jeanpiress.producao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.entities.Cliente;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long> {

}
