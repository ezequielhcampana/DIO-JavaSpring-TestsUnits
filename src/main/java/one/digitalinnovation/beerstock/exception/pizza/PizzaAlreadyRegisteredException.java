package one.digitalinnovation.beerstock.exception.pizza;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PizzaAlreadyRegisteredException extends Exception {

    public PizzaAlreadyRegisteredException(String pizzaNome) {
        super(String.format("A Pizza com nome %s já está registrado no sistema.", pizzaNome));
    }
}
