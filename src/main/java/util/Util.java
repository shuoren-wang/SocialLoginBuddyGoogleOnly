package util;

import model.Providers;

import java.util.stream.Collectors;

public class Util {
    public static String BASE_URL = "http://127.0.0.1:1234";

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

    public static String getRedirectUrl(){
        //todo
        return BASE_URL + "?";
    }
}
