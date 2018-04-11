package manager;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.core.HttpHeaders;

public class CookieHandler {
    private static final Logger LOGGER = LogManager.getLogger(CookieHandler.class);

    public static String getCookieValue(String name, HttpHeaders headers) throws Exception {
        if (StringUtils.isEmpty(name)) {
            String errMessage = "Error: Retrieve cookie failed -- Parameter name is missing";
            LOGGER.error(errMessage);
            throw new Exception(errMessage);
        }

        javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
        if (cookie == null) {
            String errMessage = "Error: Retrieve cookie failed -- Cannot find Cookie name[" + name + "]";
            LOGGER.error(errMessage);
            throw new Exception(errMessage);
        }
        return cookie.getValue();
    }

}
