package one.digitalinnovation.beerstock.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TamanhoPizza {

    BROTO("Broto"),
    PEQUENA("Pequena"),
    MEDIA("Media"),
    GRANDE("Grande"),
    HIPER("Hiper");

    private final String tamanho;
}
