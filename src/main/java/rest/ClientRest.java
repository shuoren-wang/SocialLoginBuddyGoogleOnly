package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Providers;
import util.Util;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ClientRest {
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

}
