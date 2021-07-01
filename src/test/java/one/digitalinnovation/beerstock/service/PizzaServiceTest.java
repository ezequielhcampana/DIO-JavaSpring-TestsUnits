package one.digitalinnovation.beerstock.service;

import one.digitalinnovation.beerstock.builder.PizzaDTOBuilder;
import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.entity.Pizza;
import one.digitalinnovation.beerstock.exception.pizza.PizzaNotFoundException;
import one.digitalinnovation.beerstock.exception.pizza.PizzaAlreadyRegisteredException;
import one.digitalinnovation.beerstock.mapper.PizzaMapper;
import one.digitalinnovation.beerstock.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PizzaServiceTest {

    private static final long INVALID_PIZZA_ID = 1L;

    @Mock
    private PizzaRepository pizzaRepository;

    private PizzaMapper pizzaMapper = PizzaMapper.INSTANCE;

    @InjectMocks
    private PizzaService pizzaService;

    @Test
    void whenPizzaInformedThenItShouldBeCreated() throws PizzaAlreadyRegisteredException {
        // given
        PizzaDTO expectedPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();
        Pizza expectedSavedPizza = pizzaMapper.toModel(expectedPizzaDTO);

        // when
        when(pizzaRepository.findByNome(expectedPizzaDTO.getNome())).thenReturn(Optional.empty());
        when(pizzaRepository.save(expectedSavedPizza)).thenReturn(expectedSavedPizza);

        //then
        PizzaDTO createdPizzaDTO = pizzaService.createPizza(expectedPizzaDTO);

        assertThat(createdPizzaDTO.getId(), is(equalTo(expectedPizzaDTO.getId())));
        assertThat(createdPizzaDTO.getNome(), is(equalTo(expectedPizzaDTO.getNome())));
    }

    @Test
    void whenAlreadyRegisteredPizzaInformedThenAnExceptionShouldBeThrown() {
        // given
        PizzaDTO expectedPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();
        Pizza duplicatedPizza = pizzaMapper.toModel(expectedPizzaDTO);

        // when
        when(pizzaRepository.findByNome(expectedPizzaDTO.getNome())).thenReturn(Optional.of(duplicatedPizza));

        // then
        assertThrows(PizzaAlreadyRegisteredException.class, () -> pizzaService.createPizza(expectedPizzaDTO));
    }

    @Test
    void whenValidPizzaNameIsGivenThenReturnABeer() throws PizzaNotFoundException {
        // given
        PizzaDTO expectedFoundPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();
        Pizza expectedFoundPizza = pizzaMapper.toModel(expectedFoundPizzaDTO);

        // when
        when(pizzaRepository.findByNome(expectedFoundPizza.getNome())).thenReturn(Optional.of(expectedFoundPizza));

        // then
        PizzaDTO foundPizzaDTO = pizzaService.findByNome(expectedFoundPizzaDTO.getNome());

        assertThat(foundPizzaDTO, is(equalTo(expectedFoundPizzaDTO)));
    }

    @Test
    void whenNotRegisteredPizzaNameIsGivenThenThrowAnException() {
        // given
        PizzaDTO expectedFoundPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();

        // when
        when(pizzaRepository.findByNome(expectedFoundPizzaDTO.getNome())).thenReturn(Optional.empty());

        // then
        assertThrows(PizzaNotFoundException.class, () -> pizzaService.findByNome(expectedFoundPizzaDTO.getNome()));
    }

    @Test
    void whenListPizzaIsCalledThenReturnAListOfPizzas() {
        // given
        PizzaDTO expectedFoundPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();
        Pizza expectedFoundPizza = pizzaMapper.toModel(expectedFoundPizzaDTO);

        //when
        when(pizzaRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundPizza));

        //then
        List<PizzaDTO> foundListPizzasDTO = pizzaService.listAll();

        assertThat(foundListPizzasDTO, is(not(empty())));
        assertThat(foundListPizzasDTO.get(0), is(equalTo(expectedFoundPizzaDTO)));
    }

    @Test
    void whenListPizzaIsCalledThenReturnAnEmptyListOfPizzas() {
        //when
        when(pizzaRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        //then
        List<PizzaDTO> foundListPizzasDTO = pizzaService.listAll();

        assertThat(foundListPizzasDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAPizzaShouldBeDeleted() throws PizzaNotFoundException{
        // given
        PizzaDTO expectedDeletedPizzaDTO = PizzaDTOBuilder.builder().build().toPizzaDTO();
        Pizza expectedDeletedPizza = pizzaMapper.toModel(expectedDeletedPizzaDTO);

        // when
        when(pizzaRepository.findById(expectedDeletedPizzaDTO.getId())).thenReturn(Optional.of(expectedDeletedPizza));
        doNothing().when(pizzaRepository).deleteById(expectedDeletedPizzaDTO.getId());

        // then
        pizzaService.deleteById(expectedDeletedPizzaDTO.getId());

        verify(pizzaRepository, times(1)).findById(expectedDeletedPizzaDTO.getId());
        verify(pizzaRepository, times(1)).deleteById(expectedDeletedPizzaDTO.getId());
    }

}
