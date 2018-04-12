package manager;

import model.Session;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;

public class SessionHandler {
    private static final Logger LOGGER = LogManager.getLogger(SessionHandler.class);

    public static Session getSession(String appState, HttpHeaders headers) throws Exception {
        if (headers == null) {
            String errMessage = "Error: Header is null";
            LOGGER.error(errMessage);
            throw new Exception(errMessage);
        }

        String clientState = CookieHandler.getCookieValue(appState, headers);
        String clientRedirectUri = MemcachedHandler.getInstance().getValue(appState);

        return new Session(clientRedirectUri, clientState);
    }

    public static void storeRedirectUri(String appState, String clientRedirectUri){
        //todo add case when clietState already in cache ???
        //     or change clientState to appState
        MemcachedHandler.getInstance().setKeyValue(appState, clientRedirectUri);
    }

    public static void removeState(String appState){
        MemcachedHandler.getInstance().removeKey(appState);
    }

    public static String getCookieState(String clientState){
        return "SLBCookie" + clientState;
    }
}
