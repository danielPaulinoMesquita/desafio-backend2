package br.com.daniel.testerevenda.repository;

import br.com.daniel.testerevenda.dto.CustomerOrderSummary;
import br.com.daniel.testerevenda.model.Order;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Aggregation(pipeline = {
            "{ $match: { resaleIsDone: false }}",
            "{ $unwind: '$produtos' }",
            "{ $group: { _id: '$customerId', totalQuantity: { $sum: '$produtos.quantidade' } } }",
            "{ $match: { totalQuantity: { $gte: ?0 } } }",
            "{ $project: { customerId: '$_id', totalQuantity: 1, _id: 0 } }"
    })
    List<CustomerOrderSummary> findOrdersByCustomerIdsWithTotalQuantityGreaterThan(int minQuantity);

    List<Order> findByCustomerIdIn(List<String> customerIds);

    Order findOrdersByCustomerId(String customerId);

    void deleteByCustomerId(String customerId);
}
