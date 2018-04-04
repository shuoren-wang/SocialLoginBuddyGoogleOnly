package model;

public class Session {
    private String clientSession;
    private String buddySession;
    private String providerCode;
    private TokenRequestBody token;

    public String getClientSession() {
        return clientSession;
    }

    public void setClientSession(String clientSession) {
        this.clientSession = clientSession;
    }

    public String getBuddySession() {
        return buddySession;
    }

    public void setBuddySession(String buddySession) {
        this.buddySession = buddySession;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public TokenRequestBody getToken() {
        return token;
    }

    public void setToken(TokenRequestBody token) {
        this.token = token;
    }

}
