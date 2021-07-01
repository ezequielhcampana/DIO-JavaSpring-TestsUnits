package one.digitalinnovation.beerstock.mapper;

import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PizzaMapper {

    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);
    Pizza toModel(PizzaDTO pizzaDTO);
    PizzaDTO toDTO(Pizza pizza);

}
