package com.jeanpiress.producao.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeanpiress.producao.entities.CategoriaProduto;
import com.jeanpiress.producao.services.CategoriaProdutoService;

@RunWith(MockitoJUnitRunner.class)
public class CategoriaProdutoResourceTest {

	@InjectMocks
	CategoriaProdutoResource resource;

	@Mock
	CategoriaProdutoService service;

	MockMvc mockMvc;
	
	CategoriaProduto categoria;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		
		categoria = new CategoriaProduto(1L, "Cabelo", null);
		
	}

	@Test
	public void deveBuscarTodasCategoriasEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(categoria));

		mockMvc.perform(get("/categorias")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarCategoriaPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(categoria));

		mockMvc.perform(get("/categorias/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarCategoria() throws Exception {
		Mockito.when(service.cadastrar(categoria)).thenReturn(categoria);
		
		String categoriaJson = new ObjectMapper().writeValueAsString(categoria);
		
		mockMvc.perform(post("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(categoriaJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(categoria);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmaCategoriaInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, categoria)).thenReturn(categoria);
		
		String categoriaJson = new ObjectMapper().writeValueAsString(categoria);
		
		mockMvc.perform(put("/categorias/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(categoriaJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, categoria);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmaCategoriaInformandoId() throws Exception {
		
		mockMvc.perform(delete("/categorias/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
}