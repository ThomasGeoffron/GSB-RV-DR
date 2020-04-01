package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
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
        
        /*
        String requete = "select p.pra_num,pra_nom,pra_ville,pra_coefnotoriete,r.rap_date_visite,r.rap_coef_confiance "
                + "from Praticien p "
                + "inner join RapportVisite r on p.pra_num = r.pra_num "
                + "where r.rap_coef_confiance < 5";
        */
        /*
        String requete = "select p.pra_num, pra_nom, pra_ville, pra_coefnotoriete,r.rap_date_visite,r.rap_coef_confiance"
                         + "from Praticien p inner join RapportVisite r"
                         + "on p.pra_num = r.pra_num" 
                         + "where rap_coef_confiance < 5" 
                         + "and rap_date_visite in (select rap_date_visite from RapportVisite" 
                         + "						group by pra_num" 
                         + "						order by pra_num desc)" 
                         + "group by r.pra_num"
                         + "order by rap_date_visite desc";
        */
        
        String requete = "select p.pra_num, pra_nom, pra_ville, pra_coefnotoriete,r.rap_date_visite,r.rap_coef_confiance, pra_adresse, pra_cp, pra_prenom\n" +
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
                    resultat.getInt("rap_coef_confiance"),
                    resultat.getString("pra_adresse"),
                    resultat.getString("pra_cp"),
                    resultat.getString("pra_prenom")));   
                resultat.next();
            }
                
            requetePreparee.close() ;
            return praticiens ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static Visiteur getLeVisiteur(String matricule) throws ConnexionException{
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_matricule, vis_nom, vis_prenom "
                + "from Visiteur "
                + "where vis_matricule = ?;";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1 , matricule );
            ResultSet resultat = requetePreparee.executeQuery() ;
            resultat.next();
               
            
            
            
            Visiteur visiteur = new Visiteur(resultat.getString("vis_matricule"),
                resultat.getString("vis_nom"),
                resultat.getString("vis_prenom"));
                
            requetePreparee.close() ;
            return visiteur ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static Praticien getLePraticien(int numero) throws ConnexionException{
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select p.pra_num, pra_nom, pra_ville, pra_coefnotoriete,r.rap_date_visite,r.rap_coef_confiance, pra_adresse, pra_cp, pra_prenom\n" +
                         "from Praticien p inner join RapportVisite r\n" +
                         "on p.pra_num = r.pra_num\n" +
                         "where p.pra_num = ?";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setInt( 1 , numero );
            ResultSet resultat = requetePreparee.executeQuery() ;
            resultat.next();
               
            
            
            
            Praticien praticien = new Praticien(
                ""+resultat.getInt("p.pra_num"),
                resultat.getString("pra_nom"),
                resultat.getString("pra_ville"),
                resultat.getInt("pra_coefnotoriete"),
                resultat.getDate("r.rap_date_visite").toLocalDate(),
                resultat.getInt("r.rap_coef_confiance"),
                resultat.getString("pra_adresse"),
                resultat.getString("pra_cp"),
                resultat.getString("pra_prenom"));
                
            requetePreparee.close() ;
            return praticien ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    
    public static List<Visiteur> getVisiteurs() throws ConnexionException{
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select vis_matricule, vis_nom, vis_prenom "
                + "from Visiteur;";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            resultat.last();
            int index = resultat.getRow();
            index = index -1;
            resultat.beforeFirst();
            resultat.next();
               
            List<Visiteur>visiteurs = new ArrayList<Visiteur>();
            
            for(int i = 0;i<=index; i++){
                visiteurs.add(new Visiteur(resultat.getString("vis_matricule"),
                    resultat.getString("vis_nom"),
                    resultat.getString("vis_prenom")));
                resultat.next();
            }
                
            requetePreparee.close() ;
            return visiteurs ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static List<RapportVisite> getRapportsVisite(String matricule, int mois, int annee) throws ConnexionException{
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "select rap_num, rap_date_visite, rap_date_redaction, rap_bilan, mot_libelle ,rap_coef_confiance, rap_lu, vis_matricule, pra_num "
                + "from RapportVisite r "
                + "inner join Motif m on r.mot_id = m.mot_id "
                + "where YEAR(rap_date_visite) = ? "
                + "and MONTH(rap_date_visite) = ? "
                + "and vis_matricule = ? ;";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setInt( 1 , annee );
            requetePreparee.setInt( 2 , mois);
            requetePreparee.setString( 3 , matricule );
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            
               
            List<RapportVisite>rapportsVisite = new ArrayList<RapportVisite>();
            
            while(resultat.next()){
                
                boolean rap_lu = false;
                
                if(resultat.getString("rap_lu").equals("oui")){
                    rap_lu = true;
                }
                System.out.println(resultat.getString("vis_matricule"));
                rapportsVisite.add(new RapportVisite(resultat.getInt("rap_num"),
                    resultat.getDate("rap_date_visite").toLocalDate(),
                    resultat.getDate("rap_date_redaction").toLocalDate(),
                    resultat.getString("rap_bilan"),
                    resultat.getString("mot_libelle"),
                    resultat.getString("rap_coef_confiance"),
                    rap_lu,
                    getLeVisiteur(resultat.getString("vis_matricule")),
                    getLePraticien(resultat.getInt("pra_num"))));
                
            }
                
            requetePreparee.close() ;
            return rapportsVisite ;
            

        }
        catch( SQLException e ){
            return null ;
        } 
    }
    
    public static String setRapportVisiteLu(String matricule, int numRapport) throws ConnexionException{
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "update RapportVisite "
                + "set rap_lu = \"oui\" "
                + "where rap_lu != \"oui\" "
                + "and rap_num = ? "
                + "and vis_matricule = ?";
        
        try{
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setInt( 1 ,
                    numRapport );
            requetePreparee.setString(2 , matricule);
            
            
            ResultSet resultat = requetePreparee.executeQuery() ;
            return "Opération réussie";
        }
        catch(SQLException e){
            return "ERREUR";
        }
        
    }
}
