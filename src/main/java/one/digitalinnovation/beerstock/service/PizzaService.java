package one.digitalinnovation.beerstock.service;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.entity.Pizza;
import one.digitalinnovation.beerstock.exception.BeerNotFoundException;
import one.digitalinnovation.beerstock.exception.pizza.PizzaAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.pizza.PizzaNotFoundException;
import one.digitalinnovation.beerstock.mapper.PizzaMapper;
import one.digitalinnovation.beerstock.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaMapper pizzaMapper = PizzaMapper.INSTANCE;

    private void verifyIfIsAlreadyRegistrered(String nome) throws PizzaAlreadyRegisteredException {
        Optional<Pizza> optSavedPizza = pizzaRepository.findByNome(nome);
        if (optSavedPizza.isPresent()) {
            throw new PizzaAlreadyRegisteredException(nome);
        }
    }

    public PizzaDTO createPizza(PizzaDTO pizzaDTO) throws PizzaAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(pizzaDTO.getNome());
        Pizza pizza = pizzaMapper.toModel(pizzaDTO);
        Pizza savedPizza = pizzaRepository.save(pizza);
        return pizzaMapper.toDTO(savedPizza);
    }

    public List<PizzaDTO> listAll() {
        return pizzaRepository.findAll()
                .stream()
                .map(pizzaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws PizzaNotFoundException {
        verifyIfExists(id);
        pizzaRepository.deleteById(id);
    }

    public PizzaDTO findByNome(String nome) throws PizzaNotFoundException {

        Pizza foundPizza = pizzaRepository.findByNome(nome)
                .orElseThrow(() -> new PizzaNotFoundException(nome));
        return pizzaMapper.toDTO(foundPizza);
    }

    private void verifyIfIsAlreadyRegistered(String nome) throws PizzaAlreadyRegisteredException {
        Optional<Pizza> optSavedPizza = pizzaRepository.findByNome(nome);
        if (optSavedPizza.isPresent()) {
            throw new PizzaAlreadyRegisteredException(nome);
        }
    }

    private Pizza verifyIfExists(Long id) throws PizzaNotFoundException {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new PizzaNotFoundException(id));
    }
}
