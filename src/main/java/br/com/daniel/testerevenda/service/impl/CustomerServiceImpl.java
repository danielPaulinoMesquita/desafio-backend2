package br.com.daniel.testerevenda.service.impl;

import br.com.daniel.testerevenda.dto.request.CustomerRequest;
import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.exceptions.CustomerNotException;
import br.com.daniel.testerevenda.mapper.CustomerMapper;
import br.com.daniel.testerevenda.model.Customer;
import br.com.daniel.testerevenda.repository.CustomerRepository;
import br.com.daniel.testerevenda.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void save(CustomerRequest customerRequest) {
        customerRepository.save(customerMapper.toCustomer(customerRequest));
    }

    @Override
    public List<CustomerResponse> findAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customerMapper::toCustomerResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerResponse findById(String id) {
        return customerMapper.toCustomerResponse(
                customerRepository.findById(id)
                        .orElseThrow(() -> new CustomerNotException("Cliente não encontrado, cadastre um cliente para revenda.")));
    }

    @Override
    public CustomerResponse update(String id, UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = find(id);

        return customerMapper
                .toCustomerResponse(customerRepository
                .save(customerMapper.update(updateCustomerRequest, customer)));
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(find(id).getId());
    }

    private Customer find(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotException("Cliente não encontrado, cadastre um cliente para revenda."));
    }
}
