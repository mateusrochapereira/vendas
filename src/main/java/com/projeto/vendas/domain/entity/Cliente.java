package com.projeto.vendas.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Entity
@Table(name = "cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length =  100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.cpf.obrigatorio}")
    @Column(name = "cpf", length = 11)
    @CPF(message = "{campo.cpf.invalido}")
    private String cpf;

    //fetch lazy é pra nao trazer uma lista de pedidos quando obter um cliente
    @JsonIgnore   //para o json ignorar e essa propriedade nao vir quando busacr um cliente
    @OneToMany(mappedBy = "cliente",fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
