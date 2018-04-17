package model;

import java.util.List;
import java.util.stream.Collectors;

public class Config {
    private List<String> clients;
    private List<Provider> providers;

    public Config() {}

    public List<String> getProviderNames(){
        return providers.stream()
                .map(Provider::getLoginHint)
                .collect(Collectors.toList());
    }

    public Provider getProviderByName(String name){
        return providers.stream()
                .filter(provider -> provider.getLoginHint().equals(name))
                .findFirst()
                .orElse(null);
    }
}
