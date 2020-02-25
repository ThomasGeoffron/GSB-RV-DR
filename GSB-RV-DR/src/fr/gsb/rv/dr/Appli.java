/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

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

/**
 *
 * @author developpeur
 */
public class Appli extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        BorderPane root = new BorderPane();
        
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
            if(LaReponse == "Oui"){
                Platform.exit();
            }
            
        });
        
        
        
        //action du bouton SeDeconnecter
        itemSeDeconnecter.setOnAction(actionEvent ->{
            itemSeDeconnecter.setDisable(true);
            itemSeConnecter.setDisable(false);
            menuRapports.setDisable(true);
            menuPraticiens.setDisable(true);
        });
        
        //action du bouton Se Connecter
        itemSeConnecter.setOnAction(actionEvent ->{
            itemSeConnecter.setDisable(true);
            itemSeDeconnecter.setDisable(false);
            menuRapports.setDisable(false);
            menuPraticiens.setDisable(false);
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
        
        root.setTop(barreMenu);
        
        Scene scene = new Scene(root, 300, 250);
        
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
