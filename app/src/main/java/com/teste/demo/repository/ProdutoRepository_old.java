package com.teste.demo.repository;

import com.teste.demo.model.Produto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProdutoRepository_old {

    private List<Produto> produtos = new ArrayList<>();
    private Integer ultimoId = 0;

    /**
     * Método para obter todos os produtos
     *
     * @return lista de produtos
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Métodos para obter um produto por ID
     *
     * @param id do produto a ser localizado
     * @return produto encontrado ou vazio se não encontrado
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Método para adicionar um novo produto
     *
     * @param produto a ser adicionado
     * @return produto adicionado com ID gerado
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);
        return produto;
    }

    /**
     * Método para remover um produto por ID
     *
     * @param id do produto a ser removido
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId().equals(id));
    }

    /**
     * Método para atualizar um produto existente
     *
     * @param produto com dados atualizados
     * @return produto atualizado
     */
    public Produto atualizar(Produto produto) {
        Optional<Produto> produtoExistente = obterPorId(produto.getId());
        if (produtoExistente.isEmpty()) {
            throw new InputMismatchException("Produto não encontrado");
        }

        deletar(produto.getId());
        produtos.add(produto);
        return produto;
    }
}
