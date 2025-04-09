package br.com.daniel.testerevenda.dto.response;

import br.com.daniel.testerevenda.dto.Contato;

import java.util.Set;

public record CustomerResponse(
        String id,
        String cnpj,
        String razaoSocial,
        String nomeFantasia,
        String email,
        Set<String> telefone,
        Set<Contato> nomesDeContato,
        Set<String> enderecoDeEntrega) {
}