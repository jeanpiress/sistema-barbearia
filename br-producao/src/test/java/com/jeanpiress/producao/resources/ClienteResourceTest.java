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
import com.jeanpiress.producao.entities.Cliente;
import com.jeanpiress.producao.services.ClienteService;

@RunWith(MockitoJUnitRunner.class)
public class ClienteResourceTest {

	@InjectMocks
	ClienteResource resource;

	@Mock
	ClienteService service;

	MockMvc mockMvc;
	
	Cliente cliente;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		
		cliente = new Cliente(1L, "Carol", "(34)999708382", null, null, 100, null, "cabelo cacheado", null);
		
		
	}

	@Test
	public void deveBuscarTodosOsClientesEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(cliente));

		mockMvc.perform(get("/clientes")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarClientePorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(cliente));

		mockMvc.perform(get("/clientes/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoCliente() throws Exception {
		Mockito.when(service.cadastrar(cliente)).thenReturn(cliente);
		
		String clienteJson = new ObjectMapper().writeValueAsString(cliente);
		
		mockMvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(clienteJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(cliente);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOCliente() throws Exception {
				
		mockMvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmClienteInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, cliente)).thenReturn(cliente);
		
		String clienteJson = new ObjectMapper().writeValueAsString(cliente);
		
		mockMvc.perform(put("/clientes/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(clienteJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, cliente);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmClienteSemPassarOId() throws Exception {
		String clienteJson = new ObjectMapper().writeValueAsString(cliente);
		
		mockMvc.perform(put("/clientes/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(clienteJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmClienteSemPassarOClienteAlterado() throws Exception {
		
		
		mockMvc.perform(put("/clientes/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmClienteInformandoId() throws Exception {
		
		mockMvc.perform(delete("/clientes/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmClienteSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/clientes/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}