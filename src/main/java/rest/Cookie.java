package rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path("cookie")
public class Cookie {
    private static final Logger LOGGER = LogManager.getLogger(Cookie.class);

    //todo do redirect and store at the same time??
    @POST
    @Path("create")
    public Response createCookie(
            @QueryParam("name") String name,
            @QueryParam("value") String value
    ) {
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
            String errMessage = "Error: Parameter name or value is empty";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        return Response
                .ok()
                .cookie(new NewCookie(name, value))
                .build();
    }

    @GET
    @Path("retrieve")
    public Response getRetrieveCookie(
            @CookieParam("name") String value
    ) {
        if (value == null) {
            String errMessage = "Error: Parameter name or value is empty";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        } else {
            return Response.ok(value).build();
        }
    }

    @GET
    @Path("getallcookies")
    public Response list(@Context HttpHeaders headers){
        for (String name : headers.getCookies().keySet()) {
            javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
            System.out.println("Cookie: " + name + "=" + cookie.getValue());
        }
        return Response.ok().build();
    }

    @GET
    public void get(@Context HttpHeaders hh) {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        Map<String, javax.ws.rs.core.Cookie> pathParams = hh.getCookies();
    }
}