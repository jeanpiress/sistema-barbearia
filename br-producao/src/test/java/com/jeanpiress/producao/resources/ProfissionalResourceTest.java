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
import com.jeanpiress.producao.entities.Profissional;
import com.jeanpiress.producao.services.ProfissionalService;

@RunWith(MockitoJUnitRunner.class)
public class ProfissionalResourceTest {

	@InjectMocks
	ProfissionalResource resource;

	@Mock
	ProfissionalService service;

	MockMvc mockMvc;
	
	Profissional profissional;
	
	Profissional profissionalAlterado;
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		
		profissional = new Profissional(1L, "Jean", "(34)999708382", null, null, null);
		
		profissionalAlterado= new Profissional(1L, "Jean Pires", "(34)999708382", null, null, null);
		
	}

	@Test
	public void deveBuscarTodosOsProfissionalsEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(profissional));

		mockMvc.perform(get("/profissionais")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarProfissionalPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(profissional));

		mockMvc.perform(get("/profissionais/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoProfissional() throws Exception {
		Mockito.when(service.cadastrar(profissional)).thenReturn(profissional);
		
		String profissionalJson = new ObjectMapper().writeValueAsString(profissional);
		
		mockMvc.perform(post("/profissionais")
				.contentType(MediaType.APPLICATION_JSON)
				.content(profissionalJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(profissional);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOProfissional() throws Exception {
				
		mockMvc.perform(post("/profissionais")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmProfissionalInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, profissional)).thenReturn(profissionalAlterado);
		
		String profissionalJson = new ObjectMapper().writeValueAsString(profissionalAlterado);
		
		mockMvc.perform(put("/profissionais/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(profissionalJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, profissional);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmProfissionalSemPassarOId() throws Exception {
		String profissionalJson = new ObjectMapper().writeValueAsString(profissionalAlterado);
		
		mockMvc.perform(put("/profissionais/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(profissionalJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmProfissionalSemPassarOProfissionalAlterado() throws Exception {
		
		
		mockMvc.perform(put("/profissionais/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmProfissionalInformandoId() throws Exception {
		
		mockMvc.perform(delete("/profissionais/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmProfissionalSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/profissionais/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
		
	
}