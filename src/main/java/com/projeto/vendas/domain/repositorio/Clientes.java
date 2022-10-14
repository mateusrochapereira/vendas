package com.projeto.vendas.domain.repositorio;

import domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL= "select * from cliente";

    //o jdbc template permite alguma alterações na base de dados
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[] { cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        // o mapRow mapeia o resultado do banco de dados para a classe e manda os resultados para o resultset
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");

                return new Cliente(id, nome);
            }
        });
    }
}
