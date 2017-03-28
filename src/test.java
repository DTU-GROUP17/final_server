import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;


@Path("/auth")
public class test {

    @GET
    @Produces("application/json")
    public String getClichedMessage() {
        return "Hello World";
    }

}
