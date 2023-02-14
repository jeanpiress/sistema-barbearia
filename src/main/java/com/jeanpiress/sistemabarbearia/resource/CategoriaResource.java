package com.jeanpiress.sistemabarbearia.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanpiress.sistemabarbearia.entities.Categoria;
import com.jeanpiress.sistemabarbearia.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> buscar(){
	List<Categoria> categoria = service.buscar();
	return ResponseEntity.ok().body(categoria);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id){
		Categoria categoria = service.buscarPorId(id);
		return ResponseEntity.ok().body(categoria);
	}
}
