package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Provider;
import model.Providers;
import util.ParameterConstant;
import util.Util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Rest {
    private static final ObjectMapper mapper = new ObjectMapper();

    @GET
    @Path("authentication")
    public Response getAuthentication(
            @QueryParam("state") String state
    ){
        //todo call UI
        return null;
    }


    /**
     * UI request a list of provider names
     * @return a list of provider names
     */
    @GET
    @Path("providers/name")
    public Response getProviderNames(){
        Providers providers = Util.loadProviders();

        if(providers == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No provider configuration found!").build();
        }

        return Response.ok(Util.getNameJsonStr(providers), MediaType.APPLICATION_JSON).build();
    }



    /**
     * - given provider name return provider config
     * @return
     */
    @GET
    @Path("providers/config")
    public Response getProviderConfig() throws JsonProcessingException {
        Providers providers = Util.loadProviders();

        if(providers == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No provider configuration found!").build();
        }

        String json = mapper.writeValueAsString(providers);
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    /**
     * UI request API to send authentication request to Social Provider with buddyXyz and provider config
     *
     * - create session = buddyXyz
     * - associate buddyXyz = clientXyz
     *
     * return
     */
    public Response sendAuthenticationToProvider(
            @QueryParam("state") String state,
            @QueryParam("name") String providerName){
        Provider provider = Util.loadProviders().getProviderByName(providerName);

        //send provider config, example
        //https://accounts.google.com/o/oauth2/v2/auth?
        // client_id=424911365001.apps.googleusercontent.com&
        // response_type=code&
        // scope=openid%20email&
        // redirect_uri=https://oauth2-login-demo.example.com/code&
        // state=security_token%3D138r5719ru3e1%26url%3Dhttps://oauth2-login-demo.example.com/myHome&
        // login_hint=jsmith@example.com&
        // openid.realm=example.com&
        // nonce=0394852-3190485-2490358&
        // hd=example.com
        Client client = ClientBuilder.newClient();
        Response response = client
                .target(provider.getAuthorization_endpoint())
                .queryParam(ParameterConstant.CLIENT_ID, provider.getClient_id())
                .queryParam(ParameterConstant.CLIENT_SECRET, provider.getClient_secret())
                .queryParam(ParameterConstant.REDIRECT_URL, Util.getRedirectUrl())
                .queryParam(ParameterConstant.RESPONSE_TYPE, provider.getResponse_type())
                .queryParam(ParameterConstant.SCOPE, provider.getScope())
                .queryParam(ParameterConstant.STATE, )
                .queryParam()
                .request()
                .get();

        return null;
    }

    private Response getServiceWithLogin(String url, UriType uriType) {
        String absUrl = getAbsoluteUrl(url, uriType);
        Client client = ClientBuilder.newClient();
        return client.target(absUrl).request().header(AUTH_HEADER, authValue).get();
    }

    private Response postServiceWithLogin(String url, Form form, UriType uriType) {
        String absUrl = this.getAbsoluteUrl(url, uriType);
        Client client = ClientBuilder.newClient();
        return client.target(absUrl).request().header(AUTH_HEADER, authValue).post(Entity.form(form));
    }

    private String configUrlBuilder(){

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
