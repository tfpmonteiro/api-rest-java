package com.teste.demo.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private String observacao;
}
