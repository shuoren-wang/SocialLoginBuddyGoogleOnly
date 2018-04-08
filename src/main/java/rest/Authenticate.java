package rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.Constant;
import util.TestConstant;
import util.Util;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;


@Path("authenticate")
public class Authenticate {
    private static final Logger LOGGER = LogManager.getLogger(Authenticate.class);

    @GET
    @Path("/")
    public Response redirectToSocialServer(
            @QueryParam("state") String clientState,
            @QueryParam("redirect_uri") String redirectUri
    ) throws URISyntaxException {
        if(StringUtils.isEmpty(clientState) || StringUtils.isEmpty(redirectUri)) {
            //todo: try ping url, if timeout => throw error
            String errMessage = "Error: Missing state or redirect_uri";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        URI location = new URI(getGoogleAuthorizationUrl(getState(clientState, redirectUri)));
        return Response.temporaryRedirect(location).build();

    }

    private String getState(String clientState, String clientRedirectUri){
        return clientState + clientRedirectUri;
    }

    private String getGoogleAuthorizationUrl(String state){
        return  "https://accounts.google.com/o/oauth2/v2/auth?" +
                Constant.CLIENT_ID + "=" + TestConstant.CLIENT_ID +
                "&" +
                Constant.RESPONSE_TYPE + "=" + "code" +
                "&" +
                "scope=" + TestConstant.SCOPE_VALUE +
                "&" +
                "redirect_uri=" + Util.REDIRECT_URL +
                "&" +
                "state=" + state;
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