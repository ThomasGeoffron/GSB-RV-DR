/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;

import fr.gsb.rv.dr.entites.Visiteur;

/**
 *
 * @author developpeur
 */
public class Session {
    private static Session session = null;
    private Visiteur leVisiteur;
    
    private Session(Visiteur leVisiteur){
        this.leVisiteur = leVisiteur;
    }
    
    public static void ouvrir(Visiteur leVisiteur){
        
        
    }
    
    public static void fermer(){
        
    }

    public static Session getSession() {
        if(session == null){
            new Session();
        }
        return session;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }
    
    public boolean estOuverte(){
        return false;
    }
    
    
}
