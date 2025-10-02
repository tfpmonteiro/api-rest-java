package com.teste.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Produto {

    private Integer id;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private String observacao;
}
