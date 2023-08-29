package com.jeanpiress.brFinanceiro.resources;

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
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.services.CredorService;

@RunWith(MockitoJUnitRunner.class)
public class CredorResourceTest {

	@InjectMocks
	CredorResource resource;

	@Mock
	CredorService service;

	MockMvc mockMvc;
	
	Credor credor;
			
	ObjectMapper objectMapper;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		credor = new Credor(1L, "loreal", "produtos");
				
		objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		
	}

	@Test
	public void deveBuscarTodosOsCredorsEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(credor));

		mockMvc.perform(get("/credores")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarCredorPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(credor));

		mockMvc.perform(get("/credores/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoCredor() throws Exception {
		Mockito.when(service.cadastrar(credor)).thenReturn(credor);
		
		String credorJson = objectMapper.writeValueAsString(credor);
		
		mockMvc.perform(post("/credores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(credorJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(credor);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOCredor() throws Exception {
				
		mockMvc.perform(post("/credores")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmCredorInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, credor)).thenReturn(credor);
		
		String credorJson = objectMapper.writeValueAsString(credor);
		
		mockMvc.perform(put("/credores/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(credorJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, credor);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmCredorSemPassarOId() throws Exception {
		String credorJson = objectMapper.writeValueAsString(credor);
		
		mockMvc.perform(put("/credores/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(credorJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmCredorSemPassarOCredorAlterado() throws Exception {
		
		
		mockMvc.perform(put("/credores/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmCredorInformandoId() throws Exception {
		
		mockMvc.perform(delete("/credores/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmCredorSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/credores/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}