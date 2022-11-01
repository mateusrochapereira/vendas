package com.projeto.vendas.domain.repository;



import com.projeto.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface ClientesRepository extends JpaRepository<Cliente, Integer> {


    List<Cliente> findByNomeLike(String nome);
}
