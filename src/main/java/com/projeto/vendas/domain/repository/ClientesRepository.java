package com.projeto.vendas.domain.repository;


import com.projeto.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
   /// like tem a msm função q no sql, de buscar algo que contenha o pedaço da string
    @Query(value = " select c from Cliente  c where c.nome like :nome ")
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query("delete from Cliente c where c.nome = :nome")
    @Modifying
    void deleteByNome(String nome);

    //left join é pra trazer tendo ou nao pedidos
    @Query(" select c from Cliente c left join fetch c.pedidos where c.id =:id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);


}
