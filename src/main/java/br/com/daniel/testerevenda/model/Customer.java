package br.com.daniel.testerevenda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String email;
    private Set<String> telefone;
    private Set<ContatoModel> nomesDeContato;
    private Set<String> enderecoDeEntrega;

}
