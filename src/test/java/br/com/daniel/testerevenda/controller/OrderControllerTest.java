package br.com.daniel.testerevenda.controller;

import br.com.daniel.testerevenda.model.Customer;
import br.com.daniel.testerevenda.repository.CustomerRepository;
import br.com.daniel.testerevenda.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static br.com.daniel.testerevenda.utils.FactoryUtils.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderControllerTest {

    public static final String BASE_URI = "/api/order";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        Customer customer = getCustomerModel();
        customerRepository.save(customer);
    }

    @AfterEach
    void tearDown(){
        customerRepository.deleteByCnpj(getCustomerModel().getCnpj());
    }

    @Test
    void testSaveCustomerWithSuccess() throws Exception {
        String customerId = customerRepository.findAll().get(0).getId();

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(getOrderRequest(customerId))))
                .andExpect(status().isCreated());

        orderRepository.deleteByCustomerId(customerId);
    }

    @Test
    void testSaveCustomerNotFound() throws Exception {
        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(getOrderRequest("111"))))
                .andExpect(status().isNotFound());
    }

}
