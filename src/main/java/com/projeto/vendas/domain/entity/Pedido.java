package com.projeto.vendas.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "pedido")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //só pra garantir a brunch
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    //como vai ser mapeado com o precision o numero 10000.00
    @Column(name = "total", precision = 20, scale = 2)
    private BigDecimal total;

    //quando nãp há chave na tabela da relação pra essa classe vc usa o mappedby
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;


}
