package com.teste.demo.service;

import com.teste.demo.model.Produto;
import com.teste.demo.model.exception.ResourceNotFoundException;
import com.teste.demo.repository.ProdutoRepository;
import com.teste.demo.repository.ProdutoRepository_old;
import com.teste.demo.shared.ProdutoDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private ModelMapper mapper = new ModelMapper();

    public List<ProdutoDTO> obterTodos() {

        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produto -> mapper.map(produto, ProdutoDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterPorId(Integer id) {

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado.");

        ProdutoDTO produtoDto = mapper.map(produto.get(), ProdutoDTO.class);
        return Optional.of(produtoDto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        produtoDto.setId(null);
        Produto model = mapper.map(produtoDto, Produto.class);
        model = produtoRepository.save(model);

        produtoDto.setId(model.getId());
        return produtoDto;
    }

    public void deletar(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            throw new ResourceNotFoundException("Produto com ID " + id + " não encontrado.");


        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        produtoDto.setId(id);
        Produto produto = mapper.map(produtoDto, Produto.class);
        produtoRepository.save(produto);
        return produtoDto;
    }
}
