package com.projeto.vendas;










import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.repository.ClientesRepository;
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
            clientesRepository.save(new Cliente("Mateus"));
            clientesRepository.save(new Cliente("Felipe"));


            List<Cliente> result = clientesRepository.encontrarPorNome("Mateus");
            result.forEach(System.out::println);



//            System.out.println("Atualizando  clientes");
//            todosClientes.forEach(c -> {
//                c.setNome(c.getNome() + " atualizado");
//                clientesRepository.save(c);
//
//            });
//
//            todosClientes = clientesRepository.findAll();
//            todosClientes.forEach(System.out::println);
//
//            System.out.println("buscando cliente");
//            clientesRepository.findByNomeLike("Mat").forEach(System.out::println);
//
//            System.out.println("Deletando Clientes");
//            clientesRepository.findAll().forEach(c -> {
//                clientesRepository.delete(c);
//            });
//
//            System.out.println("Todos Clientes");
//            todosClientes = clientesRepository.findAll();
//            if (todosClientes.isEmpty()) {
//                System.out.println("nenhum cliente encontrado");
//            } else {
//                todosClientes.forEach(System.out::println);
//            }
//            ;

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
