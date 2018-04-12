package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("client")
public class FakeClient {

    //todo add login parameter
    @POST
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getFakeClient(
            @PathParam("id") Integer id,
            @FormParam("userinforesponse") String userInfo,
            @FormParam("id_token") String idToken,
            @FormParam("state") String state

    ) {
        String message = "#" + id + " Welcome! " ;
        message += "\n\n";
        message += "state = " + state;
        message += "\n\n";
//        message += "access_token = " + accessToken;
        message += "\n\n";
        message += "id_token = " + idToken;
        message += "\n\n";
        message += "user_info = " + userInfo;

        return Response.ok(message).build();
    }
}