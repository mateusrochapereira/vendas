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
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = " update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";

    private static  String SELECT_NOME = "select * from cliente where nome like ?";


    //o jdbc template permite alguma alterações na base de dados
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{
                cliente.getNome(), cliente.getId()});

        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        System.out.println("passou aqui");
        return  jdbcTemplate.query(SELECT_NOME,
           //     SELECT_ALL.concat( " where nome like ? "),
                new Object[]{"%" + nome + "%"},
                obterClienteMapper());


    }

    public List<Cliente> obterTodos() {
        // o mapRow mapeia o resultado do banco de dados para a classe e manda os resultados para o resultset
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private  RowMapper<Cliente> obterClienteMapper() {
        System.out.println("passou aqui 2");
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }
}
