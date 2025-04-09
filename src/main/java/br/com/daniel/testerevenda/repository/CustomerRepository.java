package br.com.daniel.testerevenda.repository;

import br.com.daniel.testerevenda.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    void deleteByCnpj(String cnpj);
}
