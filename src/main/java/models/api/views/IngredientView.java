package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicComponentView;



@Data
public class IngredientView implements View {
    private BasicComponentView component;
    private Double amount;
    private Double tolerance;

    @Override
    public View.Type getType() {
        return Type.Ingredient;
    }
}
