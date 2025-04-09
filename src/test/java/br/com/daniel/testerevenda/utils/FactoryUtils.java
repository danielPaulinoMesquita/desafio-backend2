package br.com.daniel.testerevenda.utils;

import br.com.daniel.testerevenda.dto.Contato;
import br.com.daniel.testerevenda.dto.CustomerOrderSummary;
import br.com.daniel.testerevenda.dto.Produto;
import br.com.daniel.testerevenda.dto.request.CustomerRequest;
import br.com.daniel.testerevenda.dto.request.OrderRequest;
import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.dto.response.OrderResponse;
import br.com.daniel.testerevenda.dto.response.ResaleResponse;
import br.com.daniel.testerevenda.model.ContatoModel;
import br.com.daniel.testerevenda.model.Customer;
import br.com.daniel.testerevenda.model.Order;
import br.com.daniel.testerevenda.model.ProdutoModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Set;

public class FactoryUtils {

    public static Customer getCustomerModel() {
        return new Customer(null,
                "131231",
                "Emp Teste",
                "EMP TESTE",
                "teste@gmail.com",
                Set.of("13123","123131"),
                Set.of(new ContatoModel(false,"Fulano"),
                        new ContatoModel(true, "Sicrano")),
                Set.of("Bairro 1"));
    }

    public static CustomerRequest getCustomerRequest() {
        return new CustomerRequest("131231",
                "Emp Teste",
                "EMP TESTE",
                "teste@gmail.com",
                Set.of("13123","123131"),
                Set.of(new Contato(false,"Fulano"),
                        new Contato(true, "Sicrano")),
                Set.of("Bairro 1"));
    }

    public static CustomerResponse getCustomerResponse() {
        return new CustomerResponse(
                "123",
                "123",
                "Emp Teste",
                "EMP TESTE",
                "teste@gmail.com",
                Set.of("13123","123131"),
                Set.of(new Contato(false,"Fulano"),
                        new Contato(true, "Sicrano")),
                Set.of("Bairro 1"));
    }

    public static UpdateCustomerRequest getCustomerUpdateRequest() {
        UpdateCustomerRequest updateCustomerRequest = new UpdateCustomerRequest();
        updateCustomerRequest.setEmail("novo@gmail.com");
        return updateCustomerRequest;
    }

    public static CustomerRequest getCustomerRequestInvalid() {
        return new CustomerRequest(null,
                "Emp Teste",
                "EMP TESTE",
                "teste@gmail.com",
                Set.of("13123","123131"),
                Set.of(new Contato(false,"Fulano"),
                        new Contato(true, "Sicrano")),
                Set.of("Bairro 1"));
    }


    public static OrderRequest getOrderRequest(String customerId) {
        return new OrderRequest(customerId, Set.of(
                new Produto(
                        "Skol",
                        "Bem Gelada",
                        20.00,
                        5)));
    }

    public static OrderResponse getOrderResponse(String customerId) {
        return new OrderResponse(customerId, List.of(
                new Produto(
                        "Skol",
                        "Bem Gelada",
                        20.00,
                        5)));
    }


    public static Order getOrder() {
        return new Order(
                "123",
                "22",
                null,
                Set.of(new ProdutoModel(
                                "Skol",
                                "Gelada",
                                10.0,
                                22),
                        new ProdutoModel(
                                "Coca",
                                "Gelada",
                                10.0,
                                22)),
                false);
    }

    public static Order getOrderResaleDone() {
        return new Order("123",
                "22",
                "123",
                Set.of(new ProdutoModel(
                                "Skol",
                                "Gelada",
                                10.0,
                                22),
                        new ProdutoModel(
                                "Coca",
                                "Gelada",
                                10.0,
                                22)),
                true);
    }

    public static ResaleResponse getResaleResponse() {
        return new ResaleResponse("123",
                List.of(new Produto("Skol", "Gelada", 10.0, 22),
                        new Produto("Coca","Gelada",10.0,22)));
    }

    public static CustomerOrderSummary getCustomerOrderSummary() {
        return new CustomerOrderSummary("123", 1800);
    }

    public static String toJson(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (final Exception e){
            throw new RuntimeException(e);
        }
    }

}
