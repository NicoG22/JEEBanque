/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;

/**
 *
 * @author Renyusan
 */
@Singleton
@LocalBean
public class initBD {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private GestionnaireDeComptebancaires gc;
    
    @PostConstruct
    public void initBase() {
        System.out.println("#### BD REMPLIE ###");
        gc.creerComptesDeTest();
    }

}
