package one.digitalinnovation.beerstock.exception.pizza;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PizzaNotFoundException extends Exception {

    public PizzaNotFoundException(String pizzaNome) {

        super(String.format("A Pizza com o nome %s não foi encontrada no sistema.", pizzaNome));

    }

    public PizzaNotFoundException(Long id) {

        super(String.format("A Pizza com o ID. %s não foi encontrada no sistema", id));

    }
}
