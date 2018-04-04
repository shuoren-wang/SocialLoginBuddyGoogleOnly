package model;

public class Session {
    private String clientSession;
    private String buddySession;
    private String providerCode;
    private Token token;
    private UserInfo userInfo;

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

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
