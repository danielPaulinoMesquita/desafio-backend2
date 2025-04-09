package br.com.daniel.testerevenda.controller;

import br.com.daniel.testerevenda.dto.request.OrderRequest;
import br.com.daniel.testerevenda.dto.response.OrderResponse;
import br.com.daniel.testerevenda.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.save(orderRequest));
    }
}
