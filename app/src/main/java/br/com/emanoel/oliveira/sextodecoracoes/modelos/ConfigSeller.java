package br.com.emanoel.oliveira.sextodecoracoes.modelos;

/**
 * Created by USUARIO on 05/06/2018.
 */

public class ConfigSeller {

    private String emailSeller;
    private String tokenSeller;
    private String userID;

    public ConfigSeller() {
    }

    public ConfigSeller(String emailSeller, String tokenSeller, String userID) {
        this.emailSeller = emailSeller;
        this.tokenSeller = tokenSeller;
        this.userID = userID;
    }

    public String getEmailSeller() {
        return emailSeller;
    }

    public void setEmailSeller(String emailSeller) {
        this.emailSeller = emailSeller;
    }

    public String getTokenSeller() {
        return tokenSeller;
    }

    public void setTokenSeller(String tokenSeller) {
        this.tokenSeller = tokenSeller;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
