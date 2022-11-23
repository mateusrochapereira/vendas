package com.projeto.vendas.rest.controller;

import com.projeto.vendas.domain.entity.Pedido;
import com.projeto.vendas.rest.dto.PedidoDTO;
import com.projeto.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping("/fazerPedido")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer savePedido(@RequestBody PedidoDTO dto){
        Pedido pedido = (Pedido) service.salvar(dto);
        return pedido.getId();
    }
}
