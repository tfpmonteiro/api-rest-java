package com.teste.demo.view.mapper;

import com.teste.demo.shared.ProdutoDTO;
import com.teste.demo.view.model.ProdutoRequest;
import com.teste.demo.view.model.ProdutoResponse;

public class ProdutoMapper {

    public ProdutoResponse toResponse(ProdutoDTO produtoDTO) {
        return new ProdutoResponse(
            produtoDTO.getId(),
            produtoDTO.getNome(),
            produtoDTO.getValor(),
            produtoDTO.getQuantidade(),
            produtoDTO.getObservacao()
        );
    }

    public ProdutoRequest toRequest(ProdutoDTO produtoDTO) {
        return new ProdutoRequest(
            produtoDTO.getNome(),
            produtoDTO.getValor(),
            produtoDTO.getQuantidade(),
            produtoDTO.getObservacao()
        );
    }

    public ProdutoDTO toDTO(ProdutoRequest produtoRequest) {
        return new ProdutoDTO(
            null,
            produtoRequest.nome(),
            produtoRequest.valor(),
            produtoRequest.quantidade(),
            produtoRequest.observacao()
        );
    }
}
