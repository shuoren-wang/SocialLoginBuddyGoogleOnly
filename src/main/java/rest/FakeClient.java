package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("client")
public class FakeClient {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getFakeClient(
            @QueryParam("id_token") String idToken,
            @QueryParam("user_info") String userInfo
    ) {
        String message = "Welcome! " ;
        message += "\n\n\n";
        message += "id_token = " + idToken;
        message += "\n\n\n";
        message += "user_info = " + userInfo;

        return Response.ok(message).build();
    }
}