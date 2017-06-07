package models.api.views;


import lombok.Data;
import models.api.views.Basic.BasicUserView;
import models.db.Weight;
import models.db.User;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class WeightView implements View{

    private Integer id;
    private String name;
    private String uri;
    private BasicUserView createdBy;
    private Timestamp createdAt;
    private BasicUserView updatedBy;
    private Timestamp updatedAt;
    private BasicUserView deletedBy;
    private Timestamp deletedAt;


    @Override
    public Type getType() {
        return Type.Weight;
    }
}
