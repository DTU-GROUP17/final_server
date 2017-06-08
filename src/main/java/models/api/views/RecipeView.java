package models.api.views;

import java.sql.Timestamp;



public class RecipeView implements View{
    private String name;
    private Timestamp  created_at;
    private Integer crated_by;
    private Timestamp deleted_at;
    private Integer deleted_by;


    @Override
    public Type getType() {return Type.Recipe;}
}




