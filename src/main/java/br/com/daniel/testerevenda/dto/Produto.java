package br.com.daniel.testerevenda.dto;

public record Produto(String nome,
                      String descricao,
                      double preco,
                      int quantidade) {
}
