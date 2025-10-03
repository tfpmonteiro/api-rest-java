package com.teste.demo.view.controller;

import com.teste.demo.model.Produto;
import com.teste.demo.service.ProdutoService;
import com.teste.demo.shared.ProdutoDTO;
import com.teste.demo.view.mapper.ProdutoMapper;
import com.teste.demo.view.model.ProdutoRequest;
import com.teste.demo.view.model.ProdutoResponse;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    private ModelMapper mapper = new ModelMapper();

    private ProdutoMapper produtoMapper = new ProdutoMapper();

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos() {

        List<ProdutoDTO> produtos = produtoService.obterTodos();
        List<ProdutoResponse> response = produtos.stream()
                .map(produtoDto -> produtoMapper.toResponse(produtoDto))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> obterPorId(@PathVariable Integer id) {
      // try {
            Optional<ProdutoDTO> produtoDto = produtoService.obterPorId(id);
            ProdutoResponse response = produtoMapper.toResponse(produtoDto.get());

            return ResponseEntity.ok(Optional.of(response));
       /* } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }*/
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest request) {
        ProdutoDTO produtoDto = produtoMapper.toDTO(request);
        produtoDto = produtoService.adicionar(produtoDto);
        ProdutoResponse response = produtoMapper.toResponse(produtoDto);
        return ResponseEntity.status(201).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest request, @PathVariable Integer id) {
        ProdutoDTO produtoDto = produtoMapper.toDTO(request);
        produtoDto = produtoService.atualizar(id, produtoDto);

        return ResponseEntity.ok(produtoMapper.toResponse(produtoDto));
    }

}
