package manager;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Token;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.Constant;
import util.TestConstant;
import util.Util;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.IOException;


public class SocialServerRequestHandler {
    private static final Logger LOGGER = LogManager.getLogger(SocialServerRequestHandler.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Token getTokenFromSocialServer(String tokenUrl, String code) throws Exception {
        Response response = ClientBuilder.newClient()
                .target(tokenUrl)
                .request()
                .post(Entity.entity(getTokenRequestBody(code), MediaType.APPLICATION_FORM_URLENCODED_TYPE));

        return getToken(tokenUrl, response);
    }

    private static String getTokenRequestBody(String code){
        String requestBody = "";
        requestBody += Constant.CLIENT_ID + "=" + TestConstant.CLIENT_ID_VALUE;
        requestBody += "&";
        requestBody += Constant.CLIENT_SECRET + "=" + TestConstant.CLIENT_SECRET_VALUE;
        requestBody += "&";
        requestBody += Constant.GRANT_TYPE + "=" + Constant.AUTHORIZATION_CODE;
        requestBody += "&";
        requestBody += Constant.REDIRECT_URL + "=" + Util.REDIRECT_URL;
        requestBody += "&";
        requestBody += Constant.CODE + "=" + code;

        return requestBody;
    }

    private static Token getToken(String url, Response response) throws Exception{
        String responseString = response.readEntity(String.class);

        if(response.getStatus() == HttpStatus.SC_OK) {
            try {
                return MAPPER.readValue(responseString, Token.class);
            } catch (JsonParseException e) {
                LOGGER.warn("unable to retrieve access token : " + e.getMessage());
                throw e;
            } catch (IOException e) {
                LOGGER.warn("unable to read response data : " + e.getMessage());
                throw e;
            }
        } else {
            String errorMessage = String.format("Unable to get data from url: %s. Error: %s", url, responseString);
            LOGGER.warn(errorMessage);
            throw new Exception(errorMessage);
        }
    }

    public static String getUserInfo(String userInfoUrl, String token) throws IllegalArgumentException {
        if(token == null){
            String errorMessage = "Token cannot be null";
            LOGGER.warn(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        Response response = ClientBuilder.newClient()
                .target(userInfoUrl)
                .register ((ClientRequestFilter) requestContext ->
                            requestContext.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get();

        return response.readEntity(String.class);
    }

}
