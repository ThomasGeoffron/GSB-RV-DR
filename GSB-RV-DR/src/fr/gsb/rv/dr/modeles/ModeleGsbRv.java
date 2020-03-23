package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
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
        
        String requete = "select p.pra_num, pra_nom, pra_ville, rap_coef_confiance, rap_date_visite, pra_coef_notoriete\n" +
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
                
                int numero = (Integer) Integer.parseInt(resultat.getString("p.pra_num"));
                String nom = resultat.getString("pra_nom");
                String ville = resultat.getString("pra_ville");
                float coefNotoriete = (Float) Float.parseFloat(resultat.getString("pra_coef_notoriete"));
                int coefConfiance = (Integer) Integer.parseInt(resultat.getString("rap_coef_confiance"));
                LocalDate dateVisite = (LocalDate) LocalDate.parse(resultat.getString("rap_date_visite"));
                
                Praticien praticien = new Praticien(numero, nom, ville, coefNotoriete, dateVisite, coefConfiance);
                
                liste.add(praticien);
                
            }
            
            requetePreparee.close() ;
            return liste;
            
        }
        
        catch( Exception e ){
            return null ;
        }
        
    }
}
