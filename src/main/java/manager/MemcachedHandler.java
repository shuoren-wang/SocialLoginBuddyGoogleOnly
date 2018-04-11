package manager;

import net.spy.memcached.MemcachedClient;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MemcachedHandler {
    private static final Logger LOGGER = LogManager.getLogger(MemcachedHandler.class);
    //todo in config file?
    private static final int TIMEOUT_IN_SEC = 3600;
    private static final String MEMCACHED_HOST = "localhost";
    private static final int MEMCACHED_PORT = 11211;

    private MemcachedClient client;
    private static MemcachedHandler INSTANCE;

    static {
        try {
            INSTANCE = new MemcachedHandler();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static MemcachedHandler getInstance() {
        return INSTANCE;
    }

    private MemcachedHandler() throws IOException {
        client = new MemcachedClient(new InetSocketAddress(MEMCACHED_HOST, MEMCACHED_PORT));
    }

    public String getValue(String key){
        return client.get(key).toString();
    }

    /**
     * @return false if key already in cache, otherwise return true
     */
    public Boolean setKeyValue(String key, String value){
        return client.set(key, TIMEOUT_IN_SEC, value).isDone();
    }
}
