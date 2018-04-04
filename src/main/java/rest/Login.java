package rest;

import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.TestConstant;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("login")
public class Login {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);


    //todo add provider as QueryParam???
    @GET
    @Path("redirect")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserInfo(
        @QueryParam("state") String state,
        @QueryParam("code") String code
    ) throws Exception {
        if(code == null || state == null){
            String errMessage = "code or state is null";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        String token = SocialServerRequest.getTokenFromServer(TestConstant.GOOGLE_URL_TOKEN,code);

        String userInfo = SocialServerRequest.getUserInfo(TestConstant.GOOGLE_URL_USERINFO, token);

        return Response.ok(userInfo).build();
    }

}