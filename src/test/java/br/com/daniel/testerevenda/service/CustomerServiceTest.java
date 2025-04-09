package br.com.daniel.testerevenda.service;

import br.com.daniel.testerevenda.exceptions.CustomerNotException;
import br.com.daniel.testerevenda.mapper.CustomerMapper;
import br.com.daniel.testerevenda.model.Customer;
import br.com.daniel.testerevenda.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static br.com.daniel.testerevenda.utils.FactoryUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockitoBean
    private CustomerRepository customerRepository;

    @MockitoBean
    private CustomerMapper customerMapper;

    @Test
    void testSaveCustomerWithSuccess() {
        when(customerMapper.toCustomer(getCustomerRequest())).thenReturn(getCustomerModel());

        customerService.save(getCustomerRequest());

        verify(customerRepository, atLeastOnce()).save(any());
    }

    @Test
    void testFindAllWithSuccess() {
        when(customerMapper.toCustomerResponse(any())).thenReturn(getCustomerResponse());

        customerService.findAll();

        verify(customerRepository, atLeastOnce()).findAll();
    }

    @Test
    void testfindByIdWithSuccess() {
        when(customerMapper.toCustomerResponse(getCustomerModel())).thenReturn(getCustomerResponse());
        when(customerRepository.findById(any())).thenReturn(Optional.of(getCustomerModel()));

        customerService.findById(any());

        verify(customerRepository, atLeastOnce()).findById(any());
    }

    @Test
    void testUpdateWithSuccess() {
        when(customerMapper.toCustomerResponse(getCustomerModel())).thenReturn(getCustomerResponse());
        when(customerRepository.findById(any())).thenReturn(Optional.of(getCustomerModel()));

        customerService.update(any(), getCustomerUpdateRequest());

        verify(customerRepository, atLeastOnce()).findById(any());
        verify(customerRepository, atLeastOnce()).save(any());
    }

    @Test
    void testDeleteWithSuccess() {
        String idCustomerSaved = "123";

        Customer customerSaved = getCustomerModel();
        customerSaved.setId(idCustomerSaved);

        when(customerMapper.toCustomerResponse(customerSaved)).thenReturn(getCustomerResponse());
        when(customerRepository.findById(idCustomerSaved)).thenReturn(Optional.of(customerSaved));

        customerService.delete(idCustomerSaved);

        verify(customerRepository).deleteById(idCustomerSaved);
    }

    @Test
    void testDeleteByIdWithException() {
        when(customerMapper.toCustomerResponse(getCustomerModel())).thenReturn(getCustomerResponse());
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        CustomerNotException customerNotException =
                assertThrows(CustomerNotException.class, () -> customerService.delete("123"));

        assertEquals("Cliente não encontrado, cadastre um cliente para revenda.", customerNotException.getMessage());
    }

}
