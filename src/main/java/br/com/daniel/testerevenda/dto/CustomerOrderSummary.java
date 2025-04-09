package br.com.daniel.testerevenda.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerOrderSummary {
    private String customerId;
    private int totalQuantity;
}
