package br.com.daniel.testerevenda.client;

import br.com.daniel.testerevenda.dto.Produto;
import br.com.daniel.testerevenda.dto.request.ResaleRequest;
import br.com.daniel.testerevenda.dto.response.ResaleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResaleCompanyFeignClientImpl implements ResaleCompanyFeignClient {

    @Override
    public ResponseEntity<ResaleResponse> getResaleOrder(ResaleRequest order) {
        return ResponseEntity.ok(new ResaleResponse("123" , List.of(
                new Produto("Skol", "Gelada", 4.00, 800),
                new Produto("Brahma", "2l", 10.00, 300)
        )));
    }
}
