/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.Session;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author developpeur
 */
public class Appli extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        // Création de la barre de menus
        MenuBar barreMenus = new MenuBar();
          
        // Création du menu Fichier et de ses items
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        
        // Création du séparateur d'item
        SeparatorMenuItem separator = new SeparatorMenuItem();
           
        MenuItem itemQuitter = new MenuItem("Quitter");
        itemQuitter.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
        
        itemQuitter.setOnAction( actionEvent ->  {
            Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
            
            alertQuitter.setTitle("Quitter");
            alertQuitter.setHeaderText("Demande de confirmation");
            alertQuitter.setContentText("Voulez-vous quittez l'application ?");
            
            ButtonType btnOui = new ButtonType("Oui");
            ButtonType btnNon = new ButtonType("Non");
                        
            alertQuitter.getButtonTypes().setAll(btnOui, btnNon);
            
            Optional<ButtonType> reponse = alertQuitter.showAndWait();
            
            if (reponse.get().getText() == "Oui")
            {
                Platform.exit();
            }
            
        });
        
        
        
        Menu menuRapports = new Menu("Rapports");
        MenuItem itemConsulter = new MenuItem("Consulter");
        
        menuRapports.getItems().add(itemConsulter);
        
        Menu menuPraticiens = new Menu("Praticiens");
        MenuItem itemPraticiens = new MenuItem("Hésitants");
        
        menuPraticiens.getItems().add(itemPraticiens);
        
        itemSeConnecter.setOnAction( actionEvent -> {
            Visiteur visiteur = new Visiteur("OB001", "BELLILI", "Oumayma");
            
            Session.ouvrir(visiteur);
            
            if (Session.estOuverte()) {
                menuFichier.getItems().remove(itemSeConnecter);
                menuFichier.getItems().add(0, itemSeDeconnecter);
                
                barreMenus.getMenus().addAll(menuRapports, menuPraticiens);
                
                primaryStage.setTitle(visiteur.getNom() + " " + visiteur.getPrenom());
                
                //System.out.println(Session.getSession().getLeVisiteur());
            }
            else {
                Alert alertQuitter = new Alert(Alert.AlertType.ERROR);
            
                alertQuitter.setTitle("Connexion impossible");
                alertQuitter.setHeaderText("Matricule ou mot de passe incorrect.");

                ButtonType btnOk = new ButtonType("ok");
                
                alertQuitter.getButtonTypes().set(0, btnOk);
            
                Optional<ButtonType> reponse = alertQuitter.showAndWait();
            }
            
            
        });
        
        itemSeDeconnecter.setOnAction( actionEvent -> {
            
            Session.fermer();
            
            if (!Session.estOuverte()) {
                menuFichier.getItems().remove(itemSeDeconnecter);
                menuFichier.getItems().add(0, itemSeConnecter);

                barreMenus.getMenus().removeAll(menuRapports, menuPraticiens);

                primaryStage.setTitle("GSB-RV-DR");
            }       
            
        });
        
        itemConsulter.setOnAction( actionEvent -> {
            
            Session session = Session.getSession();
            Visiteur visiteur = session.getLeVisiteur();
            
            System.out.println(visiteur.getNom() + " " + visiteur.getPrenom() + " [Rapports]");
            
        });
        
        itemPraticiens.setOnAction( actionEvent -> {
            
            Session session = Session.getSession();
            Visiteur visiteur = session.getLeVisiteur();
            
            System.out.println(visiteur.getNom() + " " + visiteur.getPrenom() + " [Praticiens]"); 
        
        });
        
        
        // Ajout des items dans le menu Fichier
        menuFichier.getItems().addAll(itemSeConnecter, 
            separator, itemQuitter);

        // Ajout des menus dans la barre de menus
        barreMenus.getMenus().addAll(menuFichier);
        
        
        // Positionnement de la barre de menus au bord supérieur
        root.setTop(barreMenus);
        
        Scene scene = new Scene(root, 600, 450);
        
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
