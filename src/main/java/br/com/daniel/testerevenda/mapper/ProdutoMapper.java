package br.com.daniel.testerevenda.mapper;

import br.com.daniel.testerevenda.dto.Produto;
import br.com.daniel.testerevenda.model.ProdutoModel;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS
)
public interface ProdutoMapper {
    Produto toProduto(ProdutoModel produto);
}
