package model;

/**
 * https://developers.google.com/identity/sign-in/web/reference#googleusergetbasicprofile
 * You can retrieve the properties of gapi.auth2.BasicProfile with the following methods:
 BasicProfile.getId()
 BasicProfile.getName()
 BasicProfile.getGivenName()
 BasicProfile.getFamilyName()
 BasicProfile.getImageUrl()
 BasicProfile.getEmail()
 */
public class UserInfo {
    private String id;
    private String name;
    private String givenName;
    private String familyName;
    private String imageUrl;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
