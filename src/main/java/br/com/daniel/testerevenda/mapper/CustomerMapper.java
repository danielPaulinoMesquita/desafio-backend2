package br.com.daniel.testerevenda.mapper;

import br.com.daniel.testerevenda.dto.request.CustomerRequest;
import br.com.daniel.testerevenda.dto.request.UpdateCustomerRequest;
import br.com.daniel.testerevenda.dto.response.CustomerResponse;
import br.com.daniel.testerevenda.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface CustomerMapper {

    CustomerResponse toCustomerResponse(final Customer customer);

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(final CustomerRequest customerRequest);

    @Mapping(target = "id", ignore = true)
    Customer update(UpdateCustomerRequest updateUserRequest, @MappingTarget Customer customer);
}
