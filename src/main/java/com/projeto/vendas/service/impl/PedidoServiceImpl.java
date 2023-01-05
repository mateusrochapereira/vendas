package com.projeto.vendas.service.impl;

import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.entity.ItemPedido;
import com.projeto.vendas.domain.entity.Pedido;
import com.projeto.vendas.domain.entity.Produto;
import com.projeto.vendas.domain.repository.ClientesRepository;
import com.projeto.vendas.domain.repository.ItensPedidoRepository;
import com.projeto.vendas.domain.repository.PedidoRepository;
import com.projeto.vendas.domain.repository.ProdutosRepository;
import com.projeto.vendas.exception.RegraNegocioException;
import com.projeto.vendas.rest.dto.ItemPedidoDTO;
import com.projeto.vendas.rest.dto.PedidoDTO;
import com.projeto.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getClienteId();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Codigo cliente Inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

         List<ItemPedido> itemsPedidos = conveterItems(pedido, dto.getItens());
        pedidoRepository.save(pedido);
        itensPedidoRepository.saveAll(itemsPedidos);

        pedido.setItens(itemsPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }


    private List<ItemPedido> conveterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Não é possivel realizar um pedido sem Itens.");
        }
        return items
                .stream()
                .map(dto -> {

                    Integer idProduto = dto.getProdutoId();
                Produto produto =    produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Codigo produto Inválido: " + idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
