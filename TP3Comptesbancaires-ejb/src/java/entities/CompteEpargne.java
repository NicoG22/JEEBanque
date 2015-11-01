/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;

/**
 *
 * @author Renyusan
 */
public class CompteEpargne extends CompteBancaire implements Serializable{
    
    protected long apport;

    public CompteEpargne() {
        super();
    }

    public CompteEpargne(String nom, double solde, long apport) {
        super(nom,solde);
        this.apport = apport;
        
    }

    public long getApport() {
        return apport;
    }

    public void setApport(long apport) {
        this.apport = apport;
    }
    
    
    
    
    
}
