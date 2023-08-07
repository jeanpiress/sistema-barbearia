package com.jeanpiress.profissionais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeanpiress.profissionais.entities.Profissional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long>{

}
