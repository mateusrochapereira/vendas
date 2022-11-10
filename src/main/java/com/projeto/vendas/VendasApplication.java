package com.projeto.vendas;










import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.entity.Pedido;
import com.projeto.vendas.domain.repository.ClientesRepository;
import com.projeto.vendas.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VendasApplication {

 @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository,
 @Autowired PedidoRepository pedidoRepository) {
        return args -> {
            System.out.println("Salvando  clientes");

            Cliente mateus = new Cliente("Mateus");
            clientesRepository.save(mateus);


            Pedido p = new Pedido();
            p.setCliente(mateus);
            //obtendo data atual
            p.setDataPedido(LocalDate.now() );
            p.setTotal(BigDecimal.valueOf(100));


            pedidoRepository.save(p);


//            Cliente cliente = clientesRepository.findClienteFetchPedidos(mateus.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            //imprimindo a lista com for each
           pedidoRepository.findByCliente(mateus).forEach(System.out::println);



        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
