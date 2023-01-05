package com.projeto.vendas.domain.repository;

import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
