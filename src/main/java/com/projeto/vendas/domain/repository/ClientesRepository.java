package com.projeto.vendas.domain.repository;



import com.projeto.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    //like tem a msm função q no sql, de buscar algo que contenha o pedaço da string
   // List<Cliente> findByNomeLike(String nome);
   boolean existByNome(String nome);


}
