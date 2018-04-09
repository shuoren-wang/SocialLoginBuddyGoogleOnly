package rest;

import manager.SessionHandler;
import manager.SocialServerRequestHandler;
import model.Token;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.Constant;
import util.TestConstant;
import util.Util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;


@Path("authenticate")
public class Authenticate {
    private static final Logger LOGGER = LogManager.getLogger(Authenticate.class);

    @GET
    @Path("/")
    public Response redirectToSocialServer(
            @QueryParam("state") String clientState,
            @QueryParam("redirect_uri") String clientRedirectUri
    ) throws Exception {
        if(StringUtils.isEmpty(clientState) || StringUtils.isEmpty(clientRedirectUri)) {
            //todo: try ping url, if timeout => throw error
            String errMessage = "Error: Missing state or redirect_uri";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        String appState = getState(clientState, clientRedirectUri);

        SessionHandler.addNewSession(appState, clientState, clientRedirectUri);

        URI location = new URI(getAuthorizationUrl(TestConstant.GOOGLE_URL_AUTH, appState));

        return Response.temporaryRedirect(location).build();
    }

    private String getState(String clientState, String clientRedirectUri){
        return clientState + clientRedirectUri;
    }

    private String getAuthorizationUrl(String url, String state){
        return  url + "?" +
                Constant.CLIENT_ID + "=" + TestConstant.CLIENT_ID_VALUE +
                "&" +
                Constant.RESPONSE_TYPE + "=" + Constant.CODE +
                "&" +
                Constant.SCOPE + "=" + TestConstant.SCOPE_VALUE +
                "&" +
                Constant.REDIRECT_URL + "=" + Util.REDIRECT_URL +
                "&" +
                Constant.STATE + "=" + state;
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

        Token token = SocialServerRequestHandler.getTokenFromSocialServer(TestConstant.GOOGLE_URL_TOKEN, code);
        String accessToken = token.getAccessToken();
        String idToken = token.getIdToken();
        String userInfo = SocialServerRequestHandler.getUserInfo(TestConstant.GOOGLE_URL_USERINFO, accessToken);

        //todo change to redirect
        return Response.ok(userInfo).build();

//        URI location = new URI(Util.getTempClientRedirectUrl(userInfo, idToken));
//        return Response.temporaryRedirect(location).build();
    }
}