package br.com.daniel.testerevenda.mapper;

import br.com.daniel.testerevenda.dto.request.OrderRequest;
import br.com.daniel.testerevenda.dto.response.OrderResponse;
import br.com.daniel.testerevenda.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "id", target = "orderId")
    OrderResponse toOrderResponse(Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "resaleIsDone", ignore = true)
    @Mapping(target = "orderResaleId", ignore = true)
    Order toOrder(OrderRequest orderRequest);
}
