/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CompteBancaire;
import entities.OperationBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Renyusan
 */
@Stateless
@LocalBean
public class GestionnaireDeComptebancaires {
    
    @PersistenceContext
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public GestionnaireDeComptebancaires() {
        
    }

    // Fait un insert d'un compte bancaire (entité) dans la base
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
        
    }
    
    public void creerComptesDeTest() {
        for(int i=0; i < 2000; i++) {
            String nom = "Proprio" + i;
            double solde = Math.round(Math.random() * 100000);
            
            CompteBancaire c = new CompteBancaire(nom, solde);
            
            for(int j=1; j < 30; j++) {
                c.addOperation("Opération " + j, solde);
            }
            
            creerCompte(c);
        }
    }
    
    public List<CompteBancaire> findAll() {
        Query q = em.createQuery("select c from CompteBancaire c");
        return q.getResultList();
    }
    /**
     * Renvoie les comptes entre start et start+nb
     * @param start
     * @param nb
     * @return 
     */
      public List<CompteBancaire> getComptes(int start, int nb) {
        Query q = em.createQuery("select c from CompteBancaire c");
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    } 
    
      public long getNombreDeComptes() {
         Query q = em.createQuery("select count(c) from CompteBancaire c"); 
         return (long) q.getSingleResult();
      }
      
      public List<CompteBancaire> getComptesTries(int start, int nb, String order, String champ) {
        String orderValue = "";
          if(order.equals("ASCENDING")) {
              orderValue = "ASC";
         } else {
              orderValue = "DESC";
          }
          
          String r = "";
          
          if(champ.equals("nom")) {
            r = "select c from CompteBancaire c order by c.nomProprietaire " 
                  + orderValue;
            System.out.println("TRI PAR NOM: " + r);
          } 
          else if(champ.equals("solde")) {
            r = "select c from CompteBancaire c order by c.solde " 
                + orderValue;
            System.out.println("TRI PAR SOLDE: " + r);
          } 
          Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }

      public List<CompteBancaire> getComptesFilter(int start, int nb, String champ, String valeurChamp) {
          
        String r = "";

        if(champ.equals("nom")) {
          r = "select c from CompteBancaire c where (c.nomProprietaire LIKE '" + valeurChamp + "%')";
          System.out.println("FILTRE PAR NOM: " + r);
        } 
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    } 
      
      public List<CompteBancaire> getComptesTriesEtFiltres(int start, int nb, String champTrie, String champFiltre, String valeurChamp, String order) {
          
          String orderValue = "";
          if(order.equals("ASCENDING")) {
              orderValue = "ASC";
         } else {
              orderValue = "DESC";
          } 
        
        String r = "";

        if(champFiltre.equals("nom")) {
              switch (champTrie) {
                  case "nom":
                      r = "select c from CompteBancaire c where (c.nomProprietaire LIKE '" + valeurChamp + "%') order by c.nomProprietaire "
                              + orderValue;
                      System.out.println("TRI PAR NOM: " + r);
                      break;
                  case "solde":
                      r = "select c from CompteBancaire c where (c.nomProprietaire LIKE '" + valeurChamp + "%') order by c.solde "
                              + orderValue;
                      System.out.println("TRI PAR SOLDE: " + r);
                      break;
              }
          
            System.out.println("FILTRE PAR NOM: " + r);
        } 
        Query q = em.createQuery(r);
        q.setFirstResult(start);
        q.setMaxResults(nb);
        return q.getResultList();
    }
      
    public void crediterUnCompte(int id, double montant) {
        // On va chercher un compte dans la base, il est connecté
        CompteBancaire c = em.find(CompteBancaire.class, id);
        // On update juste en faisant solde+=montant
        c.crediter(montant);
    }
    
    public void debiterUnCompte(int id, double montant) {
        // On va chercher un compte dans la base, il est connecté
        CompteBancaire c = em.find(CompteBancaire.class, id);
        // On update juste en faisant solde-=montant
        c.debiter(montant);
    }
    
    public void transferer(int id1, int id2, double montant) {
        debiterUnCompte(id1, montant);
        crediterUnCompte(id2, montant);
    }
    
    public List<OperationBancaire> getOperationsBancaires(int compteId) {
        CompteBancaire c = em.find(CompteBancaire.class, compteId);
        return c.getListeOperations();
    }
    
    public void persist(Object object) {
        em.persist(object);
    }
    
    public void supprimerCompte(CompteBancaire c) {
        // Le compte n'est peut-être pas connecté
        em.remove(em.merge(c));
    }
    
       public void supprimerCompteparId(int id) {
        CompteBancaire c = em.find(CompteBancaire.class, id);
        em.remove(c);
    }
}
