package manager;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Session;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.Util;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SessionHandler {
    private static final Logger LOGGER = LogManager.getLogger(SessionHandler.class);

//    public static void addNewSession(String appState, String clientState, String clientRedirectUri)
//            throws Exception {
//        String session = getSession(clientState, clientRedirectUri);
//
//        try {
//            Response response = ClientBuilder.newClient()
//                    .target(Util.COOKIE_URL)
//                    .queryParam(URL_PARAM_NAME, URLEncoder.encode(appState, String.valueOf(StandardCharsets.UTF_8)))
//                    .queryParam(URL_PARAM_VALUE, URLEncoder.encode(session, String.valueOf(StandardCharsets.UTF_8)))
//                    .request()
//                    .post(Entity.json(null));
//
//            if(response.getStatus() != HttpStatus.SC_OK){
//                throw new Exception(response.readEntity(String.class));
//            }
//
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//
//    }

    public static String getCookieValue(String name, HttpHeaders headers) throws Exception {
        if (StringUtils.isEmpty(name)) {
            String errMessage = "Error: Retrieve cookie fail -- Parameter name is missing";
            LOGGER.error(errMessage);
            throw new Exception(errMessage);
        } else {
            javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
            if(cookie == null){
                String errMessage = "Error: Retrieve cookie fail -- Cannot find Cookie name[" + name + "]";
                LOGGER.error(errMessage);
                throw new Exception(errMessage);
            }

            System.out.println("!!Cookie  = " + cookie.toString());
            return cookie.getValue();
        }
    }

//    public static String getClientState(String appState) throws Exception {
//
//            if(appState == null){
//                throw new Exception("App State is empty");
//            }
//
//        try {
//            Response response = ClientBuilder.newClient()
//                    .target(Util.COOKIE_URL + "/retrieve")
//                    .queryParam(URL_PARAM_NAME, URLEncoder.encode(appState, String.valueOf(StandardCharsets.UTF_8)))
//                    .request(MediaType.TEXT_PLAIN)
//                    .get();
//
//            if(response.getStatus() != HttpStatus.SC_OK){
//                throw new Exception(response.readEntity(String.class));
//            }
//
//            return response.readEntity(String.class);
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//    }
}
