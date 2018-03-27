package rest;


import javax.ws.rs.core.Response;

public class ProviderRest {
    /**
     * UI request API to send authentication request to Social Provider with buddyXyz and provider config
     *
     * - create session = buddyXyz
     * - associate buddyXyz = clientXyz
     *
     * return
     */
    public Response getAuthentication(){
        //todo
        return null;
    }


    /**
     *  302, redirect to socialloginbuddy.ca.com/login?provider=google&action=login&state=buddyXyz&code=aCode
     *
     *  - assign buddyXyz = aCode
     *  - exchange aCode for a token
     */


    /**
     * request user info with token
     */
}
