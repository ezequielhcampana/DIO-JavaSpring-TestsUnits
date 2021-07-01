package one.digitalinnovation.beerstock.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.exception.pizza.PizzaAlreadyRegisteredException;
import one.digitalinnovation.beerstock.exception.pizza.PizzaNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Gerenciamento de Pizza")
public interface PizzaControllerDocs {

    @ApiOperation(value = "Pizza creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success pizza creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    PizzaDTO createPizza(PizzaDTO beerDTO) throws PizzaAlreadyRegisteredException;

    @ApiOperation(value = "Returns pizza found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success pizza found in the system"),
            @ApiResponse(code = 404, message = "Pizza with given name not found.")
    })
    PizzaDTO findByNome(@PathVariable String nome) throws PizzaNotFoundException;

    @ApiOperation(value = "Returns a list of all pizzas registered in the system")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all pizzas registered in the system"),
    })
    List<PizzaDTO> listPizzas();

    @ApiOperation(value = "Delete a pizza found by a given valid Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Success pizza deleted in the system"),
            @ApiResponse(code = 404, message = "Pizza with given id not found.")
    })
    void deleteById(@PathVariable Long id) throws PizzaNotFoundException;
}
