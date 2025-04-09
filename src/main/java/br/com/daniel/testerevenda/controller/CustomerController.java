package br.com.daniel.testerevenda.controller;

import br.com.daniel.testerevenda.dto.request.CustomerRequest;
import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CustomerRequest customer) {
        customerService.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        List<CustomerResponse> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(customerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable String id,
                                                   @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return ResponseEntity.ok().body(customerService.update(id, updateCustomerRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
