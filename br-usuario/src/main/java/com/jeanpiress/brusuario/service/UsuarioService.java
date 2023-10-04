package com.jeanpiress.brusuario.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanpiress.brusuario.entities.Usuario;
import com.jeanpiress.brusuario.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository repository;
	
	
	public Usuario buscarPorId(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		return 	usuario.get();
	}
	
	public Usuario buscarPorUserName(String user) {
		Usuario usuario = repository.findByUserName(user);
		return usuario;
	}
}
