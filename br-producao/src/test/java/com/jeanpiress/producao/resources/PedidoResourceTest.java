package com.jeanpiress.producao.resources;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.entities.enums.FormaPagamento;
import com.jeanpiress.producao.entities.enums.PagamentoStatus;
import com.jeanpiress.producao.services.PedidoService;

@RunWith(MockitoJUnitRunner.class)
public class PedidoResourceTest {

	@InjectMocks
	PedidoResource resource;

	@Mock
	PedidoService service;

	MockMvc mockMvc;
	
	Pedido pedido;
	
	Pedido pedidoProduto;
	
	Produto produto;
	
	List<Produto> produtos = new ArrayList<>();
	
	String inicio;
	
	String fim;
	
	Instant horario;
	


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(resource).alwaysDo(print()).build();
		
		pedido = new Pedido(1L, horario, "corte", null, null, null, null, null, PagamentoStatus.PAGO, FormaPagamento.DINHEIRO, horario);
		
		pedidoProduto = new Pedido(1L, null, "corte", null, null, produtos, null, null, PagamentoStatus.PAGO, FormaPagamento.DINHEIRO, horario);
		
		produtos.add(produto);
		
		inicio = "2023:08:27";
		
		fim = "2023:08:28";
		
		horario = Instant.parse("2023-08-28T00:00:00Z");
		
		
	}

	@Test
	public void deveBuscarTodosOsPedidosEmJson() throws Exception {
		mockMvc.perform(get("/pedidos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscar();
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveBuscarPedidoPorIdEmJson() throws Exception {
		mockMvc.perform(get("/pedidos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).buscarPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveCadastrarNovoPedido() throws Exception {
	String pedidoJson = new ObjectMapper().writeValueAsString(pedido);
		
		mockMvc.perform(post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pedidoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).cadastrar(pedido);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoCadastrarCasoNaoInformarPedido() throws Exception {
		mockMvc.perform(post("/pedidos")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveAlterarUmPedidoInformandoId() throws Exception {
	String pedidoJson = new ObjectMapper().writeValueAsString(pedido);
		
		mockMvc.perform(put("/pedidos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pedidoJson))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).alterar(1L, pedido);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarCasoNaoInformarOPedidoAlterado() throws Exception {
		mockMvc.perform(put("/pedidos/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAlterarCasoNaoInformarOIdPedido() throws Exception {
		String pedidoJson = new ObjectMapper().writeValueAsString(pedido);
		
		mockMvc.perform(put("/pedidos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(pedidoJson))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDeletarUmPedidoInformandoId() throws Exception {
		
		mockMvc.perform(delete("/pedidos/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent())
				.andReturn();
		
		verify(service).deletar(1L);
		verifyNoMoreInteractions(service);
		
	}
	
	
	@Test
	public void deveDarErroAoDeletarUmPedidoSemId() throws Exception {
		
		mockMvc.perform(delete("/pedidos/"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		
	}
	
	@Test
	public void deveAdicionarUmProdutoAoPedido() throws Exception {
		mockMvc.perform(put("/pedidos/1/produto/1/add")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).adicionarProduto(1L, 1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAdicionarUmProdutoSemIdDoPedido() throws Exception {
			mockMvc.perform(put("/pedidos/produto/1/add")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveDarErroAoAdicionarUmProdutoSemId() throws Exception {
			mockMvc.perform(put("/pedidos/1/produto/add")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveRemoverUmProdutoDoPedido() throws Exception {
			mockMvc.perform(put("/pedidos/1/produto/1/remove")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).removerProduto(1L, 1L);
		verifyNoMoreInteractions(service);
		

	}
	
	@Test
	public void deveDarErroSeNaoInformarSeEAddOuRemove() throws Exception {
			mockMvc.perform(put("/pedidos/1/produto")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveInformarAComissaoGeradaPelosPedidos() throws Exception {
			mockMvc.perform(get("/pedidos/1/comissao")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).comissaoPagaPorId(1L);
		verifyNoMoreInteractions(service);
		

	}
	
	
	
	@Test
	public void deveDarErroAoInformarAComissaoSeNaoInformarOIdDoPedido() throws Exception {
			mockMvc.perform(get("/pedidos/comissao")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError())
				.andReturn();
		
		verifyNoInteractions(service);
		

	}
	
	@Test
	public void deveBuscarAComissaoPagaPorProfissionalNaDataSelecionada() throws Exception {
		mockMvc.perform(get("/pedidos/profissional/1/inicio/2023:08:27/fim/2023:08:28/comissao")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		verify(service).comissaoPagaPorPeriodo(1L, inicio, fim);
		verifyNoMoreInteractions(service);
	}
	
	
	
	
}