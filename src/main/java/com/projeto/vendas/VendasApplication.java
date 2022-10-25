package com.projeto.vendas;


import com.projeto.vendas.domain.repositorio.ClientesRepository;
import com.projeto.vendas.domain.repositorio.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository) {
        return args -> {
            System.out.println("Salvando  clientes");
            clientesRepository.salvar(new Cliente("Mateus"));
            System.out.println("cadastrou o primeiro");
            clientesRepository.salvar(new Cliente("Felipe"));


           List<Cliente> todosClientes = clientesRepository.obterTodos();
          todosClientes.forEach(System.out::println);

//            System.out.println("Atualizando  clientes");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientes.atualizar(c);
//
//            });
//
//            todosClientes = clientes.obterTodos();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("buscando cliente");
//            clientes.buscarPorNome("Mat").forEach(System.out::println);
//
//
////            System.out.println("Deletando Clientes");
////            clientes.obterTodos().forEach(c -> {
////                clientes.deletar(c);
////            });
//
//            System.out.println("Todos Clientes");
//            todosClientes = clientes.obterTodos();
//            if (todosClientes.isEmpty()) {
//                System.out.println("nenhum cliente encontrado");
//            } else {
//                todosClientes.forEach(System.out::println);
//            };

    };
   }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
