package com.teste.demo.service;

import com.teste.demo.model.Produto;
import com.teste.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    public Produto obterPorId(Integer id) {
        return produtoRepository.obterPorId(id).orElse(null);
    }

    public Produto adicionar(Produto produto) {
        return produtoRepository.adicionar(produto);
    }

    public void deletar(Integer id) {
        produtoRepository.deletar(id);
    }

    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
