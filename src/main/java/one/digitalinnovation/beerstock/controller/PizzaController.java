package one.digitalinnovation.beerstock.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.exception.pizza.PizzaAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.pizza.PizzaNotFoundException;
import one.digitalinnovation.beerstock.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pizza")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PizzaController implements PizzaControllerDocs {

    private final PizzaService pizzaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaDTO createPizza(@RequestBody @Valid PizzaDTO pizzaDTO) throws PizzaAlreadyRegisteredException {
        return pizzaService.createPizza(pizzaDTO);
    }

    @GetMapping
    public List<PizzaDTO> listPizzas() {

        return pizzaService.listAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PizzaNotFoundException {
        pizzaService.deleteById(id);
    }

    @GetMapping("/{nome}")
    public PizzaDTO findByNome(@PathVariable String nome) throws PizzaNotFoundException {
        return pizzaService.findByNome(nome);
    }
}
