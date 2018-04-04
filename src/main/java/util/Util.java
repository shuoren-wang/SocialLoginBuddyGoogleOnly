package util;

import model.Providers;

import java.util.stream.Collectors;

public class Util {
    public static String BASE_URL = "http://127.0.0.1:1234";
    public static String REDIRECT_URL = BASE_URL + "/login/redirect";

    /**
     * load provider configs from config folder
     * @return
     */
    public static Providers loadProviders(){
        //todo
        return null;
    }


    public static String getNameJsonStr(Providers providers){
        String namesStr = providers.getProviderNames().stream()
                .map (n -> "\""+ n +"\"")
                .collect(Collectors.joining(","));

        return "{ \"name\" : " + namesStr + "}";
    }

}
