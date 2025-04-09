package br.com.daniel.testerevenda.service;

import br.com.daniel.testerevenda.client.ResaleCompanyFeignClient;
import br.com.daniel.testerevenda.dto.response.OrderResponse;
import br.com.daniel.testerevenda.mapper.OrderMapper;
import br.com.daniel.testerevenda.mapper.ProdutoMapper;
import br.com.daniel.testerevenda.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static br.com.daniel.testerevenda.utils.FactoryUtils.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockitoBean
    private OrderRepository orderRepository;

    @MockitoBean
    private CustomerService customerService;

    @MockitoBean
    private OrderMapper orderMapper;

    @Test
    void testSaveOrderWithSuccess() {
        final var customerId = "123";
        final var reservationId = getOrderRequest("123");

        when(orderMapper.toOrder(getOrderRequest(customerId))).thenReturn(getOrder());
        when(orderMapper.toOrderResponse(getOrder())).thenReturn(getOrderResponse(customerId));
        when(orderRepository.save(getOrder())).thenReturn(getOrder());

        OrderResponse order = orderService.save(reservationId);

        assertNotNull(order);
        verify(orderRepository).save(getOrder());
        verify(customerService).findById(customerId);
    }

}
