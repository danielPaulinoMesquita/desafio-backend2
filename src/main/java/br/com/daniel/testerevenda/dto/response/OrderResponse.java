package br.com.daniel.testerevenda.dto.response;

import br.com.daniel.testerevenda.dto.Produto;

import java.util.List;

public record OrderResponse(String orderId, List<Produto> produtos) {
}
