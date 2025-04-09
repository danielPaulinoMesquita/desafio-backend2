package br.com.daniel.testerevenda.dto.request;

import br.com.daniel.testerevenda.dto.Contato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CustomerRequest(@NotBlank(message = "Cnpj obrigatório") String cnpj,
                              @NotBlank String razaoSocial,
                              @NotBlank String nomeFantasia,
                              @NotBlank String email,
                              Set<String> telefone,
                              @NotEmpty Set<Contato> nomesDeContato,
                              @NotEmpty Set<String> enderecoDeEntrega) {
}
