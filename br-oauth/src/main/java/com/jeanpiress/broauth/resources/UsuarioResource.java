package com.jeanpiress.broauth.resources;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.broauth.entities.Usuario;
import com.jeanpiress.broauth.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	@GetMapping(value = "/{user}")
	public ResponseEntity<Usuario> buscarPorUserName(@PathVariable String user){
	try {
		Usuario usuario = service.buscarPorUserName(user);
		return ResponseEntity.ok(usuario);
	}
	catch(IllegalArgumentException e) {
		return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
	}
		
	}
}
