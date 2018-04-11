package model;

public class Session {
    private String clientRedirectUrl;
    private String clientState;

    public Session(String clientRedirectUrl, String clientState) {
        this.clientRedirectUrl = clientRedirectUrl;
        this.clientState = clientState;
    }

    public String getClientRedirectUrl() {
        return clientRedirectUrl;
    }

    public void setClientRedirectUrl(String clientRedirectUrl) {
        this.clientRedirectUrl = clientRedirectUrl;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }
}
