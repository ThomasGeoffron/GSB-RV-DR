/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Praticien;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import java.util.Optional;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import java.sql.DriverManager;
import java.sql.ResultSet;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.Pane;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class Appli extends Application {
    
    private PanneauAccueil vueAccueil = new PanneauAccueil();
    private PanneauRapports vueRapports = new PanneauRapports();
    private PanneauPraticiens vuePraticiens = new PanneauPraticiens();
    
    @Override
    public void start(Stage primaryStage) throws ConnexionException, SQLException {
        //Visiteur leVisiteur = new Visiteur();
        
        
        
        //ConnexionBD.getConnexion();
        Session.fermer();
        
        
        /*
        Statement stmt = ConnexionBD.getConnexion().createStatement();
        ResultSet res = stmt.executeQuery("select v.vis_matricule, v.vis_nom,"
                + " v.vis_prenom from Visiteur v "
                + "inner join Travailler t on v.vis_matricule = t.vis_matricule"
                + " where t.tra_role = \"délégué\";");
        String mat = new String();
        String nom = new String();
        String prenom = new String();
        
        while (res.next()){
            mat = res.getString("vis_matricule");
            nom = res.getString("vis_nom");
            prenom = res.getString("vis_prenom");
        }
        
        Visiteur visit = new Visiteur(mat,nom,prenom);
        */
        
        //Panneaux
        StackPane pile = new StackPane();
        pile.getChildren().addAll(vueAccueil,vueRapports,vuePraticiens);
       
        
        //Création barre de navigation
        MenuBar barreMenu = new MenuBar();
        
        //Création menu deroulant "Fichier"
        Menu menuFichier = new Menu( "Fichier" );
        //Création des boutons du menu déroulant Fichier
        MenuItem itemSeConnecter = new MenuItem( "Se connecter" );
        MenuItem itemSeDeconnecter = new MenuItem( "Se Déconnecter" );
        itemSeDeconnecter.setDisable(true);
        SeparatorMenuItem separator = new SeparatorMenuItem();
        MenuItem quitter = new MenuItem( "Quitter" );
        
        //Création menu déroulant "Rapports"
        Menu menuRapports = new Menu("Rapports");
        MenuItem rapportItem = new MenuItem("Consulter");
        menuRapports.getItems().add(rapportItem);
        menuRapports.setDisable(true);
        
        //Création menu déroulant "Praticiens"
        Menu menuPraticiens = new Menu("Praticiens");
        MenuItem praticienItem = new MenuItem("Hésitants");
        menuPraticiens.getItems().add(praticienItem);
        menuPraticiens.setDisable(true);

        //action du bouton quitter
        quitter.setOnAction(actionEvent ->{
            Alert alertQuit = new Alert(Alert.AlertType.CONFIRMATION);
            alertQuit.setTitle("Quitter");
            alertQuit.setHeaderText("Demande de confirmation");
            alertQuit.setContentText("Voulez-vous quitter l'application?");
            ButtonType btnYes = new ButtonType("Oui");
            ButtonType btnNo = new ButtonType("Non");
            
            
            alertQuit.getButtonTypes().setAll( btnYes , btnNo);
            Optional<ButtonType> reponse = alertQuit.showAndWait();
            String LaReponse = reponse.get().getText();
            if("Oui".equals(LaReponse)){
                Platform.exit();
            }
            
        });
        
        //action du bouton praticiensHésitants (praticienItem)
        praticienItem.setOnAction(actionEvent->{
            //System.out.println("[\"Praticiens\"]" + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
            
            vuePraticiens.toFront();
            
        });
        
        //action du bouton rapports consulter (rapportItem)
        rapportItem.setOnAction(actionEvent ->{
            //System.out.println("[\"Rapports\"]" + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
            
            vueRapports.toFront();
        });
        
        //action du bouton SeDeconnecter
        itemSeDeconnecter.setOnAction(actionEvent ->{
            itemSeDeconnecter.setDisable(true);
            itemSeConnecter.setDisable(false);
            menuRapports.setDisable(true);
            menuPraticiens.setDisable(true);
            Session.fermer();
            //primaryStage.setTitle("GSB-RV-DR");
        });
        
        //action du bouton Se Connecter
        itemSeConnecter.setOnAction((ActionEvent actionEvent) ->{
            
            
            VueConnexion vue = new VueConnexion();
            Optional<Pair<String,String>> reponse = vue.showAndWait();
            
            if(reponse.isPresent()){
                try {
                    String[] visit = reponse.get().toString().split("=");
                    Visiteur leVisiteur = ModeleGsbRv.seConnecter(visit[0], visit[1]);
                    if(leVisiteur != null){
                        Session.ouvrir(leVisiteur);
                        itemSeDeconnecter.setDisable(false);
                        itemSeConnecter.setDisable(true);
                        menuRapports.setDisable(false);
                        menuPraticiens.setDisable(false);
                        
                    }
                    else{
                        System.out.println("Ce Visiteur n'existe pas, Veuillez réesayer");
                    }
                    
                } catch (ConnexionException ex) {
                Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
            /*
            Visiteur visi = new Visiteur();
            try {
                visi = ModeleGsbRv.seConnecter("a131","azerty");
            } catch (ConnexionException ex) {
                Logger.getLogger(Appli.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(visi.getMatricule() + " " + visi.getNom()+ " " + visi.getPrenom());
            */
            
            //Session.ouvrir(visit);
            //primaryStage.setTitle("GSB-RV-DR " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
        });
        
        //affectation de l'accélérateur du bouton quitter par la combinaison "Ctrl + X"
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        
        //affectations des éléments aux menus déroulants et a la barre de navigation
        menuFichier.getItems().add(itemSeConnecter);
        menuFichier.getItems().add(itemSeDeconnecter);
        menuFichier.getItems().add(separator);
        menuFichier.getItems().add(quitter);
        barreMenu.getMenus().add(menuFichier);
        barreMenu.getMenus().add(menuRapports);
        barreMenu.getMenus().add(menuPraticiens);
        
        BorderPane root = new BorderPane();
        
        root.setTop(barreMenu);
        vueAccueil.toFront();
        root.setCenter(pile);
        
        List<Praticien>praticiens = ModeleGsbRv.getPraticiensHesitants();
        for(Praticien unPraticien : praticiens){
            System.out.println(unPraticien);
        }
        
        Scene scene = new Scene(root, 400, 300);
               
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
