package com.projeto.vendas.rest.controller;

import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.repository.ClientesRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ClienteController {

    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    @GetMapping("/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if(cliente.isPresent()){
            return  ResponseEntity.ok(cliente.get());
        }
            return ResponseEntity.notFound().build();


    }



}
