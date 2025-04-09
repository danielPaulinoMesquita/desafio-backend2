package br.com.daniel.testerevenda.service;


import br.com.daniel.testerevenda.dto.request.CustomerRequest;
import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    void save(CustomerRequest customerRequest);
    List<CustomerResponse> findAll();
    CustomerResponse findById(String id);
    CustomerResponse update(String id, UpdateCustomerRequest updateCustomerRequest);
    void delete(String id);
}
