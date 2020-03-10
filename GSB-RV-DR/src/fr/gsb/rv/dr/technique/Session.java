/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entities.Visiteur;


/**
 *
 * @author developpeur
 */
public class Session {
    
    
    private Session  session = null;

    public Session() {
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    public static String ouvrir(Visiteur v){

        String données = v.toString();
    return  données;
    }
  
    public static void fermer(){
          
    }
    
    public Visiteur getLeVisiteur(){
        Visiteur v = new Visiteur();
        
        return v;
        
    }
    
    public  static boolean estOuvrir(){
        
        return true;
    }
    
    
}
