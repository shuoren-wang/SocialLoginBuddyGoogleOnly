package application;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import rest.Authenticate;
import util.Cookie;
import rest.FakeClient;
import util.Util;

import java.io.IOException;
import java.net.URI;


public class Application {
    private static final Logger LOGGER = LogManager.getLogger(Application.class);

    private Application() {}

    public static HttpServer startServer() throws IOException {
        ResourceConfig resourceConfig = new ResourceConfig()
                .register(Authenticate.class)
                .register(Cookie.class)
                .register(FakeClient.class)
                .register(JacksonFeature.class);

        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(Util.ROOT_URL), resourceConfig);
        server.start();
        return server;
    }

    public static void main(String[] args) {
        try {
            BasicConfigurator.configure();
            startServer();
            LOGGER.info("Server started.." + Util.getTempUrl(1));
            LOGGER.info("Server started.." + Util.getTempUrl(2));
            LOGGER.info("Server started.." + Util.getTempUrlWithParameterInRedirectUrl(2));
        } catch (Exception e) {
            LOGGER.info("Failed to start server.", e);
            System.exit(-1);
        }
    }
}
