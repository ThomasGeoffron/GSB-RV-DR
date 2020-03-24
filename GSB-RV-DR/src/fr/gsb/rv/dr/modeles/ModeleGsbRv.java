package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_matricule, vis_nom , vis_prenom "
                + "from Visiteur "
                + "where vis_matricule = ?"
                + "and vis_mdp = ?" ;
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            requetePreparee.setString(2 , mdp);
            ResultSet resultat = requetePreparee.executeQuery() ;
            if( resultat.next() ){
                Visiteur visiteur = new Visiteur() ;
                visiteur.setMatricule( matricule );
                visiteur.setNom( resultat.getString( "vis_nom" ) ) ;
                visiteur.setPrenom(resultat.getString("vis_prenom"));
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static List<Praticien> getPraticiensHesitants() throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        
        String requete = "select p.pra_num,pra_nom,pra_ville,pra_coefnotoriete,r.rap_date_visite,r.rap_coef_confiance "
                + "from Praticien p "
                + "inner join RapportVisite r on p.pra_num = r.pra_num "
                + "where r.rap_coef_confiance <= 5";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            resultat.last();
            int index = resultat.getRow();
            index = index -1;
            resultat.beforeFirst();
            resultat.next();
               
            List<Praticien>praticiens = new ArrayList<Praticien>();
            
            for(int i = 0;i<=index; i++){
                praticiens.add(new Praticien(resultat.getString("pra_num"),
                    resultat.getString("pra_nom"),
                    resultat.getString("pra_ville"),
                    resultat.getInt("pra_coefnotoriete"),
                    resultat.getDate("rap_date_visite").toLocalDate(),
                    resultat.getInt("rap_coef_confiance")));   
                resultat.next();
            }
                
            requetePreparee.close() ;
            return praticiens ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
}
