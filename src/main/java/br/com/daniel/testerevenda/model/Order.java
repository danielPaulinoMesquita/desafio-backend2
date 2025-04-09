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
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String customerId;
    private String orderResaleId;
    private Set<ProdutoModel> produtos;
    private boolean resaleIsDone;

}
