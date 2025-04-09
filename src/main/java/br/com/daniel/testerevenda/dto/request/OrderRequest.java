package br.com.daniel.testerevenda.dto.request;

import br.com.daniel.testerevenda.dto.Produto;

import java.util.Set;

public record OrderRequest(String customerId, Set<Produto> produtos) {}
