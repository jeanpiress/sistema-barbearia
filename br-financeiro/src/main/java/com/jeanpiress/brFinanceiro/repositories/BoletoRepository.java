package com.jeanpiress.brFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.brFinanceiro.entities.Boleto;

public interface BoletoRepository extends JpaRepository<Boleto, Long> {

}
