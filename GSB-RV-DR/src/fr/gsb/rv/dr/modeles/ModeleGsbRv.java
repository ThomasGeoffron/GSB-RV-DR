package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_nom, vis_prenom "
                + "from Visiteur "
                + "where vis_matricule = ? "
                + "and vis_mdp = ?";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString(2, mdp);
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom( resultat.getString( "vis_prenom" ) ) ;
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    public static List<Praticien> getPraticiensHesitants() throws ConnexionException, SQLException {
        List<Praticien> liste = new ArrayList();
        
        Connection connexion = ConnexionBD.getConnexion();
        
        String requete = "select p.pra_num, pra_nom, pra_ville, rap_coef_confiance, rap_date_visite, pra_coef_notoriete, pra_cp, pra_prenom, pra_adresse\n" +
                         "from Praticien p inner join RapportVisite r\n" +
                         "on p.pra_num = r.pra_num\n" +
                         "where rap_coef_confiance < 5\n" +
                         "and rap_date_visite in (select rap_date_visite from RapportVisite\n" +
                         "						group by pra_num\n" +
                         "						order by pra_num desc)\n" +
                         "group by r.pra_num\n" +
                         "order by rap_date_visite desc ;";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            
            ResultSetMetaData resultatMeta = resultat.getMetaData();
            
            while( resultat.next() ) {
                
                String numero = resultat.getString("p.pra_num");
                String nom = resultat.getString("pra_nom");
                String ville = resultat.getString("pra_ville");
                float coefNotoriete = (Float) Float.parseFloat(resultat.getString("pra_coef_notoriete"));
                int coefConfiance = (Integer) Integer.parseInt(resultat.getString("rap_coef_confiance"));
                LocalDate dateVisite = (LocalDate) LocalDate.parse(resultat.getString("rap_date_visite"));
                String prenom = resultat.getString("pra_prenom");
                String cp = resultat.getString("pra_cp");
                String adresse = resultat.getString("pra_adresse");
                
                Praticien praticien = new Praticien(numero, nom, ville, coefNotoriete, dateVisite, coefConfiance, adresse, cp, prenom);
                
                liste.add(praticien);
                
            }
            
            requetePreparee.close() ;
            return liste;
            
        }
        
        catch( Exception e ){
            return null ;
        }
        
    }
    
    public static List<Visiteur> getVisiteurs() throws ConnexionException {
        List<Visiteur> listeVisiteurs = new ArrayList();
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_matricule, vis_nom, vis_prenom from Visiteur";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            
            ResultSetMetaData resultatMeta = resultat.getMetaData();
            
            while (resultat.next()) {
                
                String matricule = resultat.getString("vis_matricule");
                String nom = resultat.getString("vis_nom");
                String prenom = resultat.getString("vis_prenom");
                
                Visiteur visiteur = new Visiteur(matricule, nom, prenom);
                
                listeVisiteurs.add(visiteur);
                
            }
            
            requetePreparee.close() ;
            return listeVisiteurs;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Visiteur getVisiteur(String matricule) throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_nom, vis_prenom "
                + "from Visiteur "
                + "where vis_matricule = ? ";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom( resultat.getString( "vis_prenom" ) ) ;
                
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    public static Praticien getPraticien(int numero, int rapport) throws ConnexionException {
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select p.pra_num, pra_nom, pra_ville, rap_coef_confiance, rap_date_visite, pra_coef_notoriete, pra_cp, pra_prenom, pra_adresse\n" +
                         "from Praticien p inner join RapportVisite r\n" +
                         "on p.pra_num = r.pra_num\n"
                        + "where p.pra_num = ? "
                        + "and rap_num = ?";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setInt( 1 , numero );
            requetePreparee.setInt( 2 , rapport );
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                String nom = resultat.getString("pra_nom");
                String ville = resultat.getString("pra_ville");
                float coefNotoriete = (Float) Float.parseFloat(resultat.getString("pra_coef_notoriete"));
                int coefConfiance = (Integer) Integer.parseInt(resultat.getString("rap_coef_confiance"));
                LocalDate dateVisite = (LocalDate) LocalDate.parse(resultat.getString("rap_date_visite"));
                String prenom = resultat.getString("pra_prenom");
                String cp = resultat.getString("pra_cp");
                String adresse = resultat.getString("pra_adresse");
                
                Praticien praticien = new Praticien(String.valueOf(numero), nom, ville, coefNotoriete, dateVisite, coefConfiance, adresse, cp, prenom);
                
                requetePreparee.close() ;
                return praticien ;
            }
            else {
                return null ;
            }
        }
        catch( Exception e ){
            return null ;
        } 
    }
    
    public static List<RapportVisite> getRapportsVisite(String matricule, int mois, int annee) throws ConnexionException, SQLException {
        List<RapportVisite> listeRapports = new ArrayList();
        
        Connection connexion = ConnexionBD.getConnexion();
        
        String requete = "select * from RapportVisite\n" +
                      "where vis_matricule = \"" + matricule + "\"\n" +
                      "and rap_date_visite like \"" + annee + "-";
        
        if ( mois < 10 ) {
            requete = requete + "0" + mois + "%\";";
        }
        else {
            requete = requete + mois + "%\";";
        }
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            
            ResultSetMetaData resultatMeta = resultat.getMetaData();
            
            while (resultat.next()) {
                int numero = (Integer) Integer.parseInt(resultat.getString("rap_num"));
                LocalDate dateVisite = (LocalDate) LocalDate.parse(resultat.getString("rap_date_visite"));
                LocalDate dateRedaction = (LocalDate) LocalDate.parse(resultat.getString("rap_date_redaction"));
                String bilan = resultat.getString("rap_bilan");
                String motif = resultat.getString("rap_motif");
                int coefConfiance = (Integer) Integer.parseInt(resultat.getString("rap_coef_confiance"));
                Visiteur visiteur = ModeleGsbRv.getVisiteur(matricule);
                Praticien praticien = ModeleGsbRv.getPraticien((Integer) Integer.parseInt(resultat.getString("pra_num")), numero);
                        
                boolean lu = true;
                
                if (resultat.getString("rap_lu") == null || (Integer) Integer.parseInt(resultat.getString("rap_lu")) == 0) {
                    lu = false;
                }
                                
                RapportVisite rapport = new RapportVisite(numero, dateVisite, dateRedaction, bilan, motif, coefConfiance, lu, visiteur, praticien );
                
                listeRapports.add(rapport);
                
            }
            
            requetePreparee.close() ;
            return listeRapports;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static void setRapportVisiteLu(String matricule, int numRapport) throws ConnexionException, SQLException {
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "update RapportVisite\n" +
                        "set rap_lu = 1\n" +
                        "where vis_matricule = \"" + matricule + "\"\n" +
                        "and rap_num = " + numRapport + ";";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            int resultat = requetePreparee.executeUpdate() ;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
}
