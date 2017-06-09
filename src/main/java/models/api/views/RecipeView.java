package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicUserView;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Set;


@Data
public class RecipeView implements View{
    private Integer id;
    private String name;
    private Timestamp createdAt;
    private BasicUserView createdBy;
    private Timestamp deletedAt;
    private BasicUserView deletedBy;
    private Set<IngredientView> ingredients;

    @Override
    public Type getType() {
        return Type.Recipe;
    }
}




