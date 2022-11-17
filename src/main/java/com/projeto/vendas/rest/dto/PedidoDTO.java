package com.projeto.vendas.rest.dto;


import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {

    private Integer clienteId;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;


}
