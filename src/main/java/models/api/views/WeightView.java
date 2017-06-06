package models.api.views;


import lombok.Data;
import models.db.Weight;
import models.db.User;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class WeightView implements View{

    private Integer id;
    private String name;
    private String uri;
    private User createdBy;
    private Timestamp createdAt;
    private User updatedBy;
    private Timestamp updatedAt;
    private User deletedBy;
    private Timestamp deletedAt;


    @Override
    public String getType() {
        return "weight";
    }
}
