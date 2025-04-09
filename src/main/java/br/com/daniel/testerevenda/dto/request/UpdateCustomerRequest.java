package br.com.daniel.testerevenda.dto.request;

import br.com.daniel.testerevenda.dto.Contato;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class UpdateCustomerRequest {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private Set<String> telefone;
    private Set<Contato> nomesDeContato;
    private Set<String> enderecoDeEntrega;
}
