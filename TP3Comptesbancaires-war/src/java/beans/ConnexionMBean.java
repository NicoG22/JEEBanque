/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import user.Utilisateur;

/**
 *
 * @author Renyusan
 */
@Named(value = "connexionMBean")
@SessionScoped
public class ConnexionMBean implements Serializable {

    /**
     * Creates a new instance of ConnexionMBean
     */
    public ConnexionMBean() {
    }
    
    private Utilisateur utilisateur;
    private String login;
    private String password;
    private boolean connected = false;
    private String message = "Veuillez vous identifier ";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }


    public void deconnexion() {
        connected = false;
        message = "Veuillez vous identifier :";
        try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("connexion.xhtml");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void checkLogin() {
        
        connected = (login.equals("Flo") && password.equals("mbds"));
        if (connected) {
            message = "Bienvenue, vous êtes connecté en tant que " + login + " ! ";
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("crediter.xhtml");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            message = "Mauvais login/password, veuillez recommencer ! ";
        }

    }

    
}
