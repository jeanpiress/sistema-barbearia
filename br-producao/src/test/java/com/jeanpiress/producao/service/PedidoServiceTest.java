package com.jeanpiress.producao.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.jeanpiress.producao.entities.Cliente;
import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.entities.Profissional;
import com.jeanpiress.producao.entities.enums.FormaPagamento;
import com.jeanpiress.producao.entities.enums.PagamentoStatus;
import com.jeanpiress.producao.repository.PedidoRepository;
import com.jeanpiress.producao.services.ClienteService;
import com.jeanpiress.producao.services.PedidoService;
import com.jeanpiress.producao.services.ProdutoService;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTest {

	@InjectMocks
	PedidoService service;

	@Mock
	PedidoRepository repository;
	
	@Mock
	ProdutoService produtoService;
	
	@Mock
	ClienteService clienteService;
	
	Pedido pedido; 
	
	Pedido pedido2;
	
	Pedido pedido3;
	
	Pedido pedidoAlterado;
	
	Profissional profissional;
	
	Cliente cliente;
	
	Produto corte;
	
	Produto barba;
	
	Instant dia23 = Instant.parse("2023-08-23T10:00:00Z");
	
	Instant dia24 = Instant.parse("2023-08-24T10:00:00Z");
	
	Instant agora;
	
	Instant proximoMes;
	
	List<Pedido> pedidos = new ArrayList<>();
	
	List<Pedido> variosPedidos = new ArrayList<>();
	
	List<Produto> variosProdutos = new ArrayList<>();
			
	List<Produto> produtos = new ArrayList<>();
	
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		
		cliente = new Cliente(1L, "Carol", null, null, dia24, null, 30, null, null, null);
		profissional = new Profissional(1L, "Jean", "(34) 999708382", null, null, null);
		pedido = new Pedido(1L, dia23, "Corte", cliente, profissional, produtos, 0.0, 22.5, PagamentoStatus.APAGAR, FormaPagamento.ESPERANDO);
		pedido2 = new Pedido(2L, dia23, "Corte e barba", cliente, profissional, variosProdutos,45.0, 0.0, PagamentoStatus.APAGAR, FormaPagamento.ESPERANDO);
		pedido3 = new Pedido(3L, dia24, "Corte e barba", cliente, profissional, variosProdutos, 0.0, 45.0, PagamentoStatus.APAGAR, FormaPagamento.ESPERANDO);
		pedidoAlterado = new Pedido(1L, null, "Corte e barba", cliente, null, produtos, null, 22.5, PagamentoStatus.PAGO, FormaPagamento.DINHEIRO);
		corte = new Produto(1L, "corte", 45.0, null, 50.0, false, null, null);
		barba = new Produto(2L, "barba", 45.0, null, 50.0, false, null, null);
		
		agora = Instant.now().truncatedTo(ChronoUnit.SECONDS);	
		proximoMes = Instant.now().truncatedTo(ChronoUnit.SECONDS).plus(30, ChronoUnit.DAYS);
		
		pedidos.add(pedido);
		produtos.add(barba);
		
		
		
		variosProdutos.add(corte);
		variosProdutos.add(barba);
		
		variosPedidos.add(pedido);
		variosPedidos.add(pedido2);
		variosPedidos.add(pedido3);
		
			
			
		
	}

	@Test
	public void deveBuscarTodosPedidos() {
		Mockito.when(repository.findAll()).thenReturn(pedidos);
		
        List<Pedido> pedidos = service.buscar();
        
       Assertions.assertEquals(pedidos, pedidos);
       
       verify(repository).findAll();
       verifyNoMoreInteractions(repository);

       
	}

	@Test
	public void deveBuscarPedidoPorId() {
		Mockito.when(repository.findById(pedido.getId())).thenReturn(Optional.of(pedido));
		
		Optional<Pedido> pedidoOptional = service.buscarPorId(pedido.getId());

		Assertions.assertEquals(pedidoOptional.get(), pedido);
		verify(repository).findById(pedido.getId());
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveAdicionarUmPedido() {
		Mockito.when(repository.save(pedido)).thenReturn(pedido);
		
		Pedido pedidoSalvo = service.cadastrar(pedido);

		Assertions.assertEquals(pedidoSalvo, pedido);
		verify(repository).save(pedido);
		verifyNoMoreInteractions(repository);
	}

	@Test
	public void deveAlterrarUmPedido() {
		Mockito.when(repository.save(pedido)).thenReturn(pedido);
		Mockito.when(repository.getReferenceById(pedido.getId())).thenReturn(pedido);
		
		Pedido pedido = service.alterar(1L, pedidoAlterado);

		Assertions.assertEquals(pedido.getId(), 1L);
		Assertions.assertEquals(pedido.getDescricao(), pedidoAlterado.getDescricao());
		
		
		verify(repository).save(pedido);
		verify(repository).getReferenceById(1L);
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveDeletarUmPedido() {
		service.deletar(1L);

		verify(repository).deleteById(1L);
		verifyNoMoreInteractions(repository);
}
	
	@Test
	public void deveAdicionarUmProdutoAoPedidoEAlterarValor() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pedido));
		Mockito.when(produtoService.buscarPorId(1L)).thenReturn(Optional.of(corte));
		
		service.adicionarProduto(1L, 1L);
		
		Assertions.assertEquals(pedido.getProdutos().get(1), corte);
		Assertions.assertEquals(pedido.getValorTotal(), 90.0);
				
		verify(repository).findById(1L);
		verify(produtoService).buscarPorId(1L);
		verify(repository).save(pedido);
		
		verifyNoMoreInteractions(repository,produtoService);
	}
	
	@Test
	public void deveRemoverUmProdutoDoPedidoEAlterarValor() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pedido));
		Mockito.when(produtoService.buscarPorId(2L)).thenReturn(Optional.of(barba));
		
		//Adicionado "corte" a lista de produtos que só continha "Barba"
		produtos.add(corte);
		
		//Removendo "Barba" da lista
		service.removerProduto(1L, 2L);
		
		Assertions.assertEquals(pedido.getProdutos().get(0), corte);
		Assertions.assertEquals(pedido.getValorTotal(), 45.0);
				
		verify(repository).findById(1L);
		verify(produtoService).buscarPorId(2L);
		verify(repository).save(pedido);
		
		verifyNoMoreInteractions(repository,produtoService);
	}
	
	@Test
	public void deveAtualizarValor() {
		service.atulaizarValor(pedido);
		
		Assertions.assertEquals(pedido.getValorTotal(), 45.0);
		
	}
	
	@Test
	public void deveInformarComissaoPorId() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pedido));
		
		Double comissao = service.comissaoPagaPorId(1L);
		
		Assertions.assertEquals(comissao, 22.5);
		
		verify(repository).findById(pedido.getId());
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveInformarComissaoPorPedido() {
		Double comissao = service.comissaoPagaPorPedido(pedido);
		
		Assertions.assertEquals(comissao, 22.5);
		
		verifyNoInteractions(repository);

	}
	
	
	@Test
	public void deveInformarComissaoPorPeriodo() {
		Mockito.when(service.buscar()).thenReturn(variosPedidos);
	
		
		Double comissao = service.comissaoPagaPorPeriodo(1L, "2023-08-23", "2023-08-24");
		
		Assertions.assertEquals(comissao, 112.5);
		
				
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveInformarComissaoPorMes() {
		Mockito.when(service.buscar()).thenReturn(variosPedidos);
	
		
		Double comissao = service.comissaoPagaProfissionalMes(1L, 2023, 8);
		
		Assertions.assertEquals(comissao, 112.5);
		
				
		verify(repository).findAll();
		verifyNoMoreInteractions(repository);

	}
	
	@Test
	public void deveEfetuarPagamento() {
		Mockito.when(repository.getReferenceById(1L)).thenReturn(pedido);
		Mockito.when(clienteService.atualizarPrevisaoRetorno(1L)).thenReturn(proximoMes);
		
		
		service.pagamentoPedido(1L, 1);
		
		
		Assertions.assertEquals(pedido.getPagamentoStatus(), PagamentoStatus.PAGO);
		Assertions.assertEquals(pedido.getFormaPagamento(), FormaPagamento.DINHEIRO);
		Assertions.assertEquals(pedido.getCliente().getUltimaVisita(), agora);
		Assertions.assertEquals(pedido.getCliente().getPrevisaoRetorno(), proximoMes);
		
		verify(repository).getReferenceById(1L);
		verify(repository).save(pedido);
		verify(clienteService).atualizarPrevisaoRetorno(1L);
		verifyNoMoreInteractions(repository, clienteService);
	}
}