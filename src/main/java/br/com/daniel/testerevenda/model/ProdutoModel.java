package br.com.daniel.testerevenda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class ProdutoModel{

    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
}

