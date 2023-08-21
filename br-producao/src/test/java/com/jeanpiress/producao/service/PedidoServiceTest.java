package com.jeanpiress.producao.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

import com.jeanpiress.producao.entities.Pedido;
import com.jeanpiress.producao.entities.Produto;
import com.jeanpiress.producao.repository.PedidoRepository;
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
	
	Pedido pedido; 
	
	Pedido pedidoAlterado;
	
	Produto corte;
	
	Produto barba;
	
	List<Pedido> pedidos = new ArrayList<>();
	
		
	List<Produto> produtos = new ArrayList<>();

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		pedido = new Pedido(1L, null, "Corte", null, null, produtos, 0.0);
		pedidoAlterado = new Pedido(1L, null, "Corte e barba", null, null, produtos, null);
		corte = new Produto(1L, "corte", 45.0, null, 50.0, false, null, null);
		barba = new Produto(2L, "barba", 45.0, null, 50.0, false, null, null);
				
		pedidos.add(pedido);
		produtos.add(barba);
		
		
		
		
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
	public void deveInformarComissao() {
		Mockito.when(repository.findById(1L)).thenReturn(Optional.of(pedido));
		
		Double comissao = service.comissaoPaga(1L);
		
		Assertions.assertEquals(comissao, 22.5);
		
		verify(repository).findById(pedido.getId());
		verifyNoMoreInteractions(repository);

	}
}