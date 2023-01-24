package com.projeto.vendas.rest.controller;


import com.projeto.vendas.domain.entity.Cliente;
import com.projeto.vendas.domain.entity.Produto;
import com.projeto.vendas.domain.repository.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    private ProdutosRepository produtoRepository;

    public ProdutoController(ProdutosRepository produtosRepository) {
        this.produtoRepository = produtosRepository;
    }

    @GetMapping("/buscarProdutoPorId/{id}")
    public Produto getIdProduto(@PathVariable Integer id) {
        return produtoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @PostMapping("/salvarProduto")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto saveProduto(@RequestBody @Valid Produto produto) {
        return produtoRepository.save(produto);

    }

    @DeleteMapping("/deletarProduto/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public  void deleteProduto(@PathVariable Integer id){
        produtoRepository.findById(id)
                .map( produto -> {
                    produtoRepository.delete(produto);
                    return Void.TYPE;
                })
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PutMapping("atualizarProdutos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable  @Valid Integer id,
                       @RequestBody Produto produto) {
            produtoRepository

                .findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    produtoRepository.save(produto);
                    return produtoExistente;
                }).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado"));
    }

    @GetMapping("listarProdutos")
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtoRepository.findAll(example);

    }

}
