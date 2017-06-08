package models.api.views;

import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;

/**
 * Created by durankose on 08/06/2017.
 */
public class IngredientView {
    private BasicComponentView component;
    private Double amount;
    private Double tolerance;

    @Override
    public View.Type getType() {
        return View.Type.Weight;
    }
}
