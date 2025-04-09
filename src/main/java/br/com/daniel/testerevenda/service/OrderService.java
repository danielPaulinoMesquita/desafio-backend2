package br.com.daniel.testerevenda.service;

import br.com.daniel.testerevenda.dto.request.OrderRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.dto.response.OrderResponse;

public interface OrderService {
    OrderResponse save(final OrderRequest orderRequest);
    CustomerResponse validateCustomer(final String customerId);
}
