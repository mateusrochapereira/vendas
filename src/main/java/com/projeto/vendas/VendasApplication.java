package com.projeto.vendas;


import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {
//sempre usar o bean no CommandLineRUnner ou eele nao vai ser executado
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository){
        return  args -> {

            Cliente cliente = new Cliente(null,"Fulano");
            clientesRepository.save(cliente);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
