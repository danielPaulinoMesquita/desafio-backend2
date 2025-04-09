package br.com.daniel.testerevenda.controller;

import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.model.Customer;
import br.com.daniel.testerevenda.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static br.com.daniel.testerevenda.utils.FactoryUtils.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CustomerControllerTest {

    public static final String BASE_URI = "/api/customer";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void testSaveCustomerWithSuccess() throws Exception {
        mockMvc.perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(getCustomerRequest())))
                .andExpect(status().isCreated());

        customerRepository.deleteByCnpj(getCustomerModel().getCnpj());
    }

    @Test
    void testSaveCustomerWithFailure() throws Exception {
        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(getCustomerRequestInvalid())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testFindAllCustomers() throws Exception {
        Customer customer =  getCustomerModel();
        customer.setCnpj("123456789");

        Customer customer2 =  getCustomerModel();
        customer2.setCnpj("987654321");

        customerRepository.saveAll(Arrays.asList(customer, customer2));

        mockMvc.perform(get(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))));

        customerRepository.deleteByCnpj(customer.getCnpj());
        customerRepository.deleteByCnpj(customer2.getCnpj());
    }

    @Test
    void testFindCustomerByIdWithSuccess() throws Exception {
        String customerId = customerRepository.save(getCustomerModel()).getId();

        mockMvc.perform(get(BASE_URI + "/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(customerId));

        customerRepository.deleteByCnpj(getCustomerModel().getCnpj());
    }

    @Test
    void testFindCustomerByIdWithFailure() throws Exception {
        String invalidId = "0000";

        mockMvc.perform(get(BASE_URI + "/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateCustomerWithSuccess() throws Exception {
        UpdateCustomerRequest updatedCustomerRequest = getCustomerUpdateRequest();

        Customer customer = customerRepository.save(getCustomerModel());

        mockMvc.perform(put(BASE_URI + "/" + customer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email")
                        .value(updatedCustomerRequest.getEmail()));

        customerRepository.deleteByCnpj(customer.getCnpj());
    }

    @Test
    void testUpdateCustomerWithFailure() throws Exception {
        String invalidId = "00000";
        UpdateCustomerRequest updatedCustomerRequest = getCustomerUpdateRequest();

        mockMvc.perform(put(BASE_URI + "/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(updatedCustomerRequest)))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCustomerWithSuccess() throws Exception {
        String customerId = customerRepository.save(getCustomerModel()).getId();

        mockMvc.perform(delete(BASE_URI + "/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    void testDeleteCustomerWithFailure() throws Exception {
        String invalidId = "00000";

        mockMvc.perform(delete(BASE_URI + "/" + invalidId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
