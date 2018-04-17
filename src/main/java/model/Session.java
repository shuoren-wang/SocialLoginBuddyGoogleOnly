package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Session {
    @JsonProperty("client_redirect_uri")
    private String clientRedirectUri;
    @JsonProperty("client_state")
    private String clientState;

    public Session(String clientRedirectUri, String clientState) {
        this.clientRedirectUri = clientRedirectUri;
        this.clientState = clientState;
    }

    public String getClientRedirectUri() {
        return clientRedirectUri;
    }

    public void setClientRedirectUri(String clientRedirectUri) {
        this.clientRedirectUri = clientRedirectUri;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }
}
