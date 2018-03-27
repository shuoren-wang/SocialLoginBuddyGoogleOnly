package model;

/**
 * {
 "issuer": "https://provider.com",
 "client_id": "slfgksgflsgjsgf",
 "client_secret": "adsfasfasfasdf",
 "redirect_uri": "https://myredirect.com/login?provider=provider&action=login",
 "authorization_endpoint": "https://provider.com/authorization",
 "token_endpoint": "https://provider.com/token",
 "userinfo_endpoint": "https://provider.com/userinfo",
 "jwks_uri": "https://provider.com/jwks.json",
 "scope": "openid profile email",
 "response_type": "code",
 "image_url": "https://provider.com/account_image"
 }
 */

/**
 * TODO:
 *  some config may not have all the fields list here, need to add annotation to make field optional
 */
public class Provider {
    private String name;
    private String issuer;
    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String authorization_endpoint;
    private String token_endpoint;
    private String userinfo_endpoint;
    private String jwks_uri;
    private String scope;
    private String response_type;
    private String image_url;

    public String getName() {
        return name;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getClient_id() {
        return client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public String getAuthorization_endpoint() {
        return authorization_endpoint;
    }

    public String getToken_endpoint() {
        return token_endpoint;
    }

    public String getUserinfo_endpoint() {
        return userinfo_endpoint;
    }

    public String getJwks_uri() {
        return jwks_uri;
    }

    public String getScope() {
        return scope;
    }

    public String getResponse_type() {
        return response_type;
    }

    public String getImage_url() {
        return image_url;
    }
}
