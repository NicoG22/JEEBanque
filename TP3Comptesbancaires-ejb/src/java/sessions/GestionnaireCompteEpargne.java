/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CompteEpargne;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Renyusan
 */
@Stateless
@LocalBean
public class GestionnaireCompteEpargne  {
    
    @PersistenceContext
    private EntityManager em;
    
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<CompteEpargne> findAll() {
        Query q = em.createQuery("select c from CompteEpargne c");
        return q.getResultList();
    }
    
    public void majEpargn(){
        List<CompteEpargne> listCE = this.findAll();
        for(CompteEpargne i : listCE){
            i.tauxInteret();
            em.persist(i);
        } 
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public GestionnaireCompteEpargne() {
    }
}
