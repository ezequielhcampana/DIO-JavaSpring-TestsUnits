package one.digitalinnovation.beerstock.repository;

import one.digitalinnovation.beerstock.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    Optional<Pizza> findByNome(String nome);

}
