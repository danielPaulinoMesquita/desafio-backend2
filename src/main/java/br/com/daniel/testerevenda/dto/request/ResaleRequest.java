package br.com.daniel.testerevenda.dto.request;

import br.com.daniel.testerevenda.dto.Produto;

import java.util.Set;

public record ResaleRequest(String customerId, Set<Produto> produtos) { }
