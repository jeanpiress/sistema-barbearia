package com.jeanpiress.brFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.brFinanceiro.entities.Credor;

public interface CredorRepository extends JpaRepository<Credor, Long> {

}
