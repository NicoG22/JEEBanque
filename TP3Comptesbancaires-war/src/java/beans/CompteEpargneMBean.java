/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;

/**
 *
 * @author Renyusan
 */
@Named(value = "compteEpargneMBean")
@ViewScoped
public class CompteEpargneMBean implements Serializable{
    
    

    /**
     * Creates a new instance of CompteEpargneMBean
     */
    public CompteEpargneMBean() {
    }
    
}
