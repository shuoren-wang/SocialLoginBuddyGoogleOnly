package rest;

import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.TestConstant;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;


@Path("login")
public class Login {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);

    @GET
    @Path("/")
    public Response redirectToSocialServer(
            @QueryParam("provider") String provider
    ) throws URISyntaxException {
        if("google".equalsIgnoreCase(provider)) {
            URI location = new URI(getGoogleAuthorizationUrl());
            return Response.temporaryRedirect(location).build();
        }

        String errMessage = "Error: Cannot find provider";
        LOGGER.error(errMessage);
        return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();

    }

    private String getGoogleAuthorizationUrl(){
        return  "https://accounts.google.com/o/oauth2/v2/auth?" +
                "client_id=" + TestConstant.CLIENT_ID +
                "&" +
                "response_type=code" +
                "&" +
                "scope=openid%20profile%20email" +
                "&" +
                "redirect_uri=http://127.0.0.1:1234/login/redirect" +
                "&" +
                "state=security_token%3D138r5719ru3e1%26url%3D";
    }

    @GET
    @Path("redirect")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUserInfo(
        @QueryParam("state") String state,
        @QueryParam("code") String code
    ) throws Exception {
        if(code == null || state == null){
            String errMessage = "Error: code or state is null";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        String token = SocialServerRequest.getTokenFromServer(TestConstant.GOOGLE_URL_TOKEN,code);

        String userInfo = SocialServerRequest.getUserInfo(TestConstant.GOOGLE_URL_USERINFO, token);

        return Response.ok(userInfo).build();
    }

}