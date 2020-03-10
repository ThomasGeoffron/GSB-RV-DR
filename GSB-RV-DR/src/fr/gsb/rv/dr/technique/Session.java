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
        session = new Session(leVisiteur);
        System.out.println("Ouverture de session avec l'utilisateur " + leVisiteur.getNom());
    }
    
    public static void fermer(){
        if(session == null){
            System.out.println("La session est déja fermée");
        }
        session = null;
    }

    public static Session getSession() {
        return session;
    }

    public Visiteur getLeVisiteur() {
        return leVisiteur;
    }
    
    public static boolean estOuverte(){
        if(session != null){
            return true;
        }
        else{
            return false;
        }
    }
    
    
}
