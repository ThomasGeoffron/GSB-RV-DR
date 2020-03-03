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
    
    private Session(Visiteur visiteur){
        this.leVisiteur = visiteur;
    }
    
    public static void ouvrir(Visiteur visiteur) {
        Session.session = new Session(visiteur);
    }
    
    public static void fermer() {
        Session.session = null;
    }
    
    public static Session getSession() {
        return Session.session;
    }
    
    public Visiteur getLeVisiteur() {
        return this.leVisiteur;
    }
    
    public static boolean estOuverte() {
        if (Session.session == null) {
            return false;
        }
        return true;
    }
}
