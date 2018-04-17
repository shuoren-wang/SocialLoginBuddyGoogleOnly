package util;

import manager.SessionHandler;
import model.Config;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.stream.Collectors;

public class Util {
    private static final Logger LOGGER = LogManager.getLogger(Util.class);

    public static String HOST = "shuo.socialloginbuddy.ca.com";
//    public static final String HOST = "127.0.0.1";
    public static final String ROOT_URL = "http://" + HOST + ":8080";
    public static final String AUTHENTICATE_URL = ROOT_URL + "/authenticate";
    public static final String COOKIE_URL = ROOT_URL + "/cookie";
    public static final String REDIRECT_URL = AUTHENTICATE_URL + "/redirect";
    //temporary client url
    public static String TEMP_CLIENT_REDERECT_URL = ROOT_URL + "/client";

    /**
     * load provider configs from config folder
     * @return
     */
    public static Config loadProviders(){
        //todo
        return null;
    }

    public static String getNameJsonStr(Config config){
        String namesStr = config.getProviderNames().stream()
                .map (n -> "\""+ n +"\"")
                .collect(Collectors.joining(","));

        return "{ \"name\" : " + namesStr + "}";
    }

    public static String escapeJsonString(String str){
        return str.replace("\"", "\\\"");
    }


    //todo remove or use for test
    // should get state from client
    public static String getRandomState() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    //todo remove or use for test
    public static String getTempUrl(int id){
        return Util.AUTHENTICATE_URL + "?" +
                Constant.REDIRECT_URL + "=" + TEMP_CLIENT_REDERECT_URL + "/" + id +
                "&" +
                Constant.STATE + "=" + "new_state_" + id;
//                Constant.STATE + "=" + Util.getRandomState();
    }

    public static String getTempUrlWithParameterInRedirectUrl(int id){
        return Util.AUTHENTICATE_URL + "?" +
                Constant.REDIRECT_URL + "=" + TEMP_CLIENT_REDERECT_URL + "/" + id + "?login=action" +
                "&" +
                Constant.STATE + "=" + "new_state_" + id;
    }

    //todo remove or use for test
    public static String getTempClientRedirectUrl(String clientRedirectUri, String clientState, String idToken, String userInfo, String accessToken)
            throws Exception {
        if(StringUtils.isEmpty(clientRedirectUri)){
            String errMessage = "Error: Missing clientRedirectUri";
            LOGGER.error(errMessage);
            throw new Exception(errMessage);
        }
        return clientRedirectUri +
                (clientRedirectUri.indexOf("?") > 0? "&" : "?") +
                "state=" + URLEncoder.encode(clientState, String.valueOf(StandardCharsets.UTF_8)) +
                "&" +
                "access_token=" + URLEncoder.encode(accessToken, String.valueOf(StandardCharsets.UTF_8)) +
                "&" +
                "user_info=" + URLEncoder.encode(userInfo, String.valueOf(StandardCharsets.UTF_8)) +
                "&" +
                "id_token=" + URLEncoder.encode(idToken, String.valueOf(StandardCharsets.UTF_8));
    }

}
