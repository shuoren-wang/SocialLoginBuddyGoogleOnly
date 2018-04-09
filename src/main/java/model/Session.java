package model;

public class Session {
    private String redirectUrl;
    private String clientState;

    public Session(String redirectUrl, String clientState) {
        this.redirectUrl = redirectUrl;
        this.clientState = clientState;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getClientState() {
        return clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }
}
