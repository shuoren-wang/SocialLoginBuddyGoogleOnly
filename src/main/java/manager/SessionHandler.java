package manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Session;
import org.apache.http.HttpStatus;
import util.Util;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SessionHandler {
    private static final String URL_PARAM_NAME = "name";
    private static final String URL_PARAM_VALUE = "value";


    public static void addNewSession(String appState, String clientState, String clientRedirectUri)
            throws Exception {
        String session = getSession(clientState, clientRedirectUri);

        try {
            Response response = ClientBuilder.newClient()
                    .target(Util.COOKIE_URL + "/create")
                    .queryParam(URL_PARAM_NAME, URLEncoder.encode(appState, String.valueOf(StandardCharsets.UTF_8)))
                    .queryParam(URL_PARAM_VALUE, URLEncoder.encode(session, String.valueOf(StandardCharsets.UTF_8)))
                    .request()
                    .post(Entity.json(null));

            if(response.getStatus() != HttpStatus.SC_OK){
                throw new Exception(response.readEntity(String.class));
            }

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    private static String getSession(String clientState, String clientRedirectUri) throws JsonProcessingException {
        Session session = new Session(clientState, clientRedirectUri);
        return new ObjectMapper().writeValueAsString(session);
    }
}
