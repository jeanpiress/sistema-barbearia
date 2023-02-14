package com.jeanpiress.sistemabarbearia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanpiress.sistemabarbearia.entities.Pedido;

public interface PedidoRespository extends JpaRepository<Pedido, Long>{

}
