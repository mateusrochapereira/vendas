package com.projeto.vendas.service;


import com.projeto.vendas.domain.entity.Pedido;
import com.projeto.vendas.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
}
