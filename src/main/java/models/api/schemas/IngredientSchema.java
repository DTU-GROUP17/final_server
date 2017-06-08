package models.api.schemas;

import lombok.Data;


@Data
public class IngredientSchema implements Schema {
    private Integer component;
    private Double amount;
    private Double tolerence;
}
