package br.com.daniel.testerevenda.service.impl;

import br.com.daniel.testerevenda.dto.request.OrderRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.dto.response.OrderResponse;
import br.com.daniel.testerevenda.mapper.OrderMapper;
import br.com.daniel.testerevenda.repository.OrderRepository;
import br.com.daniel.testerevenda.service.CustomerService;
import br.com.daniel.testerevenda.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            CustomerService customerService,
            OrderMapper orderMapper
    ) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        validateCustomer(orderRequest.customerId());
        return orderMapper.toOrderResponse(orderRepository.save(orderMapper.toOrder(orderRequest)));
    }

    @Override
    public CustomerResponse validateCustomer(final String customerId) {
        return customerService.findById(customerId);
    }
}
