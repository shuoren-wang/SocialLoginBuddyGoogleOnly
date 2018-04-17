package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Provider;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ConfigUtil {
    private static final Logger LOGGER = LogManager.getLogger(ConfigUtil.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String CONFIG_FILE_PATH = "config/sample_config.json";
    public static final String SOCIAL_LOGIN_BUDDY = "socialloginbuddy";
    public static final String CLIENTS = "clients";
    public static final String PROVIDERS = "providers";


    private static String getConfigFileContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(CONFIG_FILE_PATH)));
    }

    private static JsonNode getConfig() throws IOException {
        try {
            JsonNode node = MAPPER.readValue(getConfigFileContent(), JsonNode.class);
            return node.get(SOCIAL_LOGIN_BUDDY);

        } catch (IOException e) {
            LOGGER.error("Failed to read config file ::" + e.getMessage());
            throw e;
        }
    }

    public static List getClients() throws Exception {
        JsonNode clientsNode = getConfig().get(CLIENTS);
        if(clientsNode.isArray()){
            try {
                return MAPPER.readValue(clientsNode.toString(), List.class);
            }catch (Exception e){
                throw new Exception("Faile to map clients" + clientsNode.toString());
            }
        }

        throw new Exception("Fail to read clients --" +  clientsNode.toString());
    }

    public static List<Provider> getProviders() throws Exception {
        JsonNode providerNode = getConfig().get(PROVIDERS);
        if(providerNode.isArray()){
            try {
                return Arrays.asList(MAPPER.readValue(providerNode.toString(), Provider[].class));
            }catch (Exception e){
                throw new Exception("Faile to map providers" + providerNode.toString());
            }
        }

        throw new Exception("Fail to read providers --" +  providerNode.toString());
    }

    public static Provider getProviderByLoginHint(String loginHint) throws Exception {
        return getProviders().stream()
                .filter(provider -> provider.getLoginHint().equals(loginHint))
                .findFirst()
                .orElse(null);
    }


    public static void main(String[] args) throws Exception {
        ConfigUtil.getProviders();
        System.out.println(getProviderByLoginHint("google"));
    }
}
