package com.jeanpiress.brFinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.brFinanceiro.entities.GastoFixo;

public interface GastoFixoRepository extends JpaRepository<GastoFixo, Long> {

}
