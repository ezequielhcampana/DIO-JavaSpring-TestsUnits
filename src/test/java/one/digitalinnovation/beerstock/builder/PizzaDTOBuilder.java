package one.digitalinnovation.beerstock.builder;

import lombok.Builder;
import one.digitalinnovation.beerstock.dto.PizzaDTO;
import one.digitalinnovation.beerstock.enums.TamanhoPizza;

@Builder
public class PizzaDTOBuilder {

    @Builder.Default
    private Long id = 1L;
    @Builder.Default
    private String nome = "Portuguesa Especial";
    @Builder.Default
    private String descricao = "Molho, mussarela, presunto, calabresa, bacon, ervilha, milho, ovo, tomate, palmito, orégano";
    @Builder.Default
    private double preco = 48.50;  // um pouco caro essa pizza né!, srsrs...
    @Builder.Default
    private TamanhoPizza tamanhoPizza = TamanhoPizza.GRANDE;

    public PizzaDTO toPizzaDTO() {
        return new PizzaDTO(
                id,
                nome,
                descricao,
                preco,
                tamanhoPizza
        );
    }
}
