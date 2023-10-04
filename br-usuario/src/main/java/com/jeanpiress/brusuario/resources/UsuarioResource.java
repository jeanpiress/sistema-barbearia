package com.jeanpiress.brusuario.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.brusuario.entities.Usuario;
import com.jeanpiress.brusuario.service.UsuarioService;

@RestController
@RequestMapping(value= "/usuarios")
public class UsuarioResource {

	@Autowired
	UsuarioService service;
	
	
	@GetMapping(value = "/{id}")
	ResponseEntity<Usuario> buscarPorId(@PathVariable Long id){
		
		Usuario usuario = service.buscarPorId(id);
		return ResponseEntity.ok(usuario);
	}
	
	@GetMapping(value = "/user/{user}")
	ResponseEntity<Usuario> buscarPorUserName(@PathVariable String user){
		
		Usuario usuario = service.buscarPorUserName(user);
		return ResponseEntity.ok(usuario);
	}
	

}
