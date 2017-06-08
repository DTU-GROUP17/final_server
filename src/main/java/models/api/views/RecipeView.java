package models.api.views;

import lombok.Data;
import models.api.views.Basic.BasicUserView;

import java.sql.Timestamp;


@Data
public class RecipeView implements View{
    private String name;
    private Timestamp createdAt;
    private BasicUserView createdBy;
    private Timestamp deletedAt;
    private BasicUserView deletedBy;


    @Override
    public Type getType() {return Type.Recipe;}
}




