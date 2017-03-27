import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;


@Path("/helloworld")
public class test {

    @GET
    @Produces("application/json")
    public String getClichedMessage() {
        return "Hello World";
    }

}
