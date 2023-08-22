package com.jeanpiress.producao.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
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
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.services.ProdutoService;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoResourceTest {

	@InjectMocks
	ProdutoResource resource;

	@Mock
	ProdutoService service;

	MockMvc mockMvc;
	
	Produto produto;
	
	Produto produtoCategoria;
	
	CategoriaProduto categoria;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		
		produto = new Produto(1L, "Corte", 45.00, null, 50.0, false, null, null);
		
		produtoCategoria = new Produto(1L, "Corte", 45.00, null, 50.0, false, null, categoria);
		
		
	}

	@Test
	public void deveBuscarTodosOsProdutosEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(produto));

		mockMvc.perform(get("/produtos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarProdutoPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(produto));

		mockMvc.perform(get("/produtos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoProduto() throws Exception {
		Mockito.when(service.cadastrar(produto)).thenReturn(produto);
		
		String produtoJson = new ObjectMapper().writeValueAsString(produto);
		
		mockMvc.perform(post("/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(produtoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(produto);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOProduto() throws Exception {
				
		mockMvc.perform(post("/produtos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmProdutoInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, produto)).thenReturn(produto);
		
		String produtoJson = new ObjectMapper().writeValueAsString(produto);
		
		mockMvc.perform(put("/produtos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(produtoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, produto);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmProdutoSemPassarOId() throws Exception {
		String produtoJson = new ObjectMapper().writeValueAsString(produto);
		
		mockMvc.perform(put("/produtos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(produtoJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmProdutoSemPassarOProdutoAlterado() throws Exception {
		
		
		mockMvc.perform(put("/produtos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmProdutoInformandoId() throws Exception {
		
		mockMvc.perform(delete("/produtos/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmProdutoSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/produtos/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAdicionarUmaCategoriaAoProduto() throws Exception {
		Mockito.when(service.adicionarCategoria(1L, 1L)).thenReturn(produtoCategoria);
		
		mockMvc.perform(put("/produtos/1/categoria/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).adicionarCategoria(1L, 1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAdicionarUmaCategoriaAoProdutoSemPassarIdProduto() throws Exception {
				
		mockMvc.perform(put("/produtos/categoria/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoAdicionarUmaCategoriaAoProdutoSemPassarIdCategoria() throws Exception {
				
		mockMvc.perform(put("/produtos/1/categoria")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}