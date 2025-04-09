package br.com.daniel.testerevenda.client;

import br.com.daniel.testerevenda.dto.request.ResaleRequest;
import br.com.daniel.testerevenda.dto.response.ResaleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

// todo Eu usaria este Feign para fazer a requisição
//@FeignClient(
//        name = "resale",
//        url = "http://localhost:3000"
//)
//public interface ResaleCompanyFeignClient {
//
//    @PostMapping("/resale")
//    ResponseEntity<ResaleResponse> getResaleOrder(@RequestBody ResaleRequest order);
//}


public interface ResaleCompanyFeignClient {
    ResponseEntity<ResaleResponse> getResaleOrder(@RequestBody ResaleRequest order);
}

