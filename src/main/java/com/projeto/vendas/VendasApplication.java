package com.projeto.vendas;

import com.projeto.vendas.domain.repositorio.Clientes;
import domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return args -> {
            System.out.println("Salvando  clientes");
            clientes.salvar(new Cliente("Mateus"));
            clientes.salvar(new Cliente("Felipe"));


            List<Cliente> todosClientes=  clientes.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando  clientes");
            todosClientes.forEach(c-> {
                c.setNome(c.getNome()+"atualizado");
                clientes.atualizar(c);

            });

            System.out.println("buscando cliente");
            clientes.buscarPorNome("mat").forEach(System.out::println);

            //       todosClientes=  clientes.obterTodos();
            //   todosClientes.forEach(System.out::println);

            ;
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
