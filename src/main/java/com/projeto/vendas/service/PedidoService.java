package com.projeto.vendas.service;


import com.projeto.vendas.domain.entity.Pedido;
import com.projeto.vendas.domain.enums.StatusPedido;
import com.projeto.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

  Optional<Pedido> obterPedidoCompleto(Integer id);
  void atualizaStatus(Integer id, StatusPedido statusPedido);
}
