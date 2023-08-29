package com.jeanpiress.brFinanceiro.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.Instant;
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
import com.jeanpiress.brFinanceiro.entities.Boleto;
import com.jeanpiress.brFinanceiro.entities.Credor;
import com.jeanpiress.brFinanceiro.enums.PagamentoStatus;
import com.jeanpiress.brFinanceiro.services.BoletoService;

@RunWith(MockitoJUnitRunner.class)
public class BoletoResourceTest {

	@InjectMocks
	BoletoResource resource;

	@Mock
	BoletoService service;

	MockMvc mockMvc;
	
	Boleto boleto;
	
	Credor loreal;
	
	Instant dataPagamento;
	
	ObjectMapper objectMapper;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		loreal = new Credor(1L, "loreal", "produtos");
		dataPagamento = Instant.parse("2023-08-28T00:00:00Z");
		
		boleto = new Boleto(1L, loreal, 350.0, dataPagamento, PagamentoStatus.PAGO);
		
		objectMapper = new ObjectMapper();
	    objectMapper.registerModule(new JavaTimeModule());
		
		
	}

	@Test
	public void deveBuscarTodosOsBoletosEmJson() throws Exception {
		Mockito.when(service.buscar()).thenReturn(Collections.singletonList(boleto));

		mockMvc.perform(get("/boletos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarBoletoPorIdEmJson() throws Exception {
		Mockito.when(service.buscarPorId(1L)).thenReturn(Optional.ofNullable(boleto));

		mockMvc.perform(get("/boletos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	

	@Test
	public void deveCadastrarNovoBoleto() throws Exception {
		Mockito.when(service.cadastrar(boleto)).thenReturn(boleto);
		
		String boletoJson = objectMapper.writeValueAsString(boleto);
		
		mockMvc.perform(post("/boletos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(boletoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(boleto);
		verifyNoMoreInteractions(service);
		

	}
	
	
	@Test
	public void deveDarErroAoCadastrarSemInformarOBoleto() throws Exception {
				
		mockMvc.perform(post("/boletos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmBoletoInformandoId() throws Exception {
		Mockito.when(service.alterar(1L, boleto)).thenReturn(boleto);
		
		String boletoJson = objectMapper.writeValueAsString(boleto);
		
		mockMvc.perform(put("/boletos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(boletoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, boleto);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmBoletoSemPassarOId() throws Exception {
		String boletoJson = objectMapper.writeValueAsString(boleto);
		
		mockMvc.perform(put("/boletos/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(boletoJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarUmBoletoSemPassarOBoletoAlterado() throws Exception {
		
		
		mockMvc.perform(put("/boletos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmBoletoInformandoId() throws Exception {
		
		mockMvc.perform(delete("/boletos/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoDeletarUmBoletoSemPassarOId() throws Exception {
		
		mockMvc.perform(delete("/boletos/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
}