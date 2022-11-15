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

    @GetMapping("/api/clientePorId/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/salvarCliente")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {

        Cliente clienteSalvo = clientesRepository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);

    }

    @DeleteMapping("/api/deletarCliente/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);

        if (cliente.isPresent()) {
            clientesRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/atualizarCliente/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,
                                 @RequestBody Cliente cliente) {
//é uma forma de usar um update com o .map
        return clientesRepository
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientesRepository.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

