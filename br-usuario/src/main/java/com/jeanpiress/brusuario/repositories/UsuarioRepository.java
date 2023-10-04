package com.jeanpiress.brusuario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.brusuario.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Usuario findByUserName(String userName);
	}
