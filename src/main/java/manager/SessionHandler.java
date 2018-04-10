package manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;

public class SessionHandler {
    private static final Logger LOGGER = LogManager.getLogger(SessionHandler.class);

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

}
