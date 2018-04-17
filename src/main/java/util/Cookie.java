package util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Date;


@Path("cookie")
public class Cookie {
    private static final Logger LOGGER = LogManager.getLogger(Cookie.class);

    @POST
    @Path("create")
    public Response createCookie(
            @QueryParam("name") String name,
            @QueryParam("value") String value
    ) {
        if(StringUtils.isEmpty(name) || StringUtils.isEmpty(value)){
            String errMessage = "Error: Create cookie fail -- Parameter name or value is missing";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        }

        System.out.println("!! Created Cookie: " + name + "=" + value);
        return Response
                .ok()
                .cookie(new NewCookie(name, value))
                .build();
    }

    @GET
    @Path("retrieve")
    public Response getRetrieveCookie(
            @QueryParam("name") String name,
            @Context HttpHeaders headers
    ) {
        if (StringUtils.isEmpty(name)) {
            String errMessage = "Error: Retrieve cookie fail -- Parameter name is missing";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        } else {
            javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
            if(cookie == null){
                String errMessage = "Error: Retrieve cookie fail -- Cannot find Cookie name[" + name + "]";
                LOGGER.error(errMessage);
                return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
            }

            return Response.ok(cookie).build();
        }
    }

    @GET
    @Path("delete")
    public Response deleteCookie(
            @QueryParam("name") String name,
            @Context HttpHeaders headers
    ) {
        if (StringUtils.isEmpty(name)) {
            String errMessage = "Error: Retrieve cookie fail -- Parameter name is missing";
            LOGGER.error(errMessage);
            return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
        } else {
            javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
            if(cookie == null){
                String errMessage = "Error: Retrieve cookie fail -- Cannot find Cookie name[" + name + "]";
                LOGGER.error(errMessage);
                return Response.status(HttpStatus.SC_FORBIDDEN).entity(errMessage).build();
            }

            return Response
                    .ok()
//                    public NewCookie(String name, String value, String path, String domain, String comment, int maxAge, boolean secure)
                    .cookie(new NewCookie(headers.getCookies().get(name), "deleted", 0, new Date(0), false, false))
                    .build();
        }
    }

    @GET
    public Response getAllCookies(@Context HttpHeaders headers){
        for (String name : headers.getCookies().keySet()) {
            javax.ws.rs.core.Cookie cookie = headers.getCookies().get(name);
            System.out.println("Cookie: " + name + "=" + cookie.getValue());
        }
        return Response.ok().build();
    }
}