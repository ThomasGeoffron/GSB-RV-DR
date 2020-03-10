/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gsb.rv.dr;

import java.util.Collection;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 *
 * @author developpeur
 */
public class GSBRVDR extends Application {
    
    //méthod d'implementation boite de dialogue
    private void confirmation(){

            Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
            alertQuitter.setTitle("Quitter");
            alertQuitter.setHeaderText("demande de confirmation");
            alertQuitter.setContentText("Voulez-vous quitter l'application?");
            
            
            ButtonType btnOui = new ButtonType("Oui");
            ButtonType btnNon = new ButtonType("Non");
           
            alertQuitter.getButtonTypes().setAll( btnOui, btnNon);
            Optional<ButtonType>reponse=alertQuitter.showAndWait();
            reponse.get();
            
        } 
    
    private void seConnecter(){
        
       
        }
    
    @Override
    public void start(Stage primaryStage) {
         
         //creation du barre de menu
        MenuBar barreMenu = new MenuBar();
        
        //creation du menu
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports = new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");

        //creation du l'item du menu
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se deconnecter");
        MenuItem itemQuitter = new MenuItem("Quitter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitant = new MenuItem("Hésitants");
        
        //ajout de l'item dans le menu
        menuFichier.getItems().addAll(itemSeConnecter, itemSeDeconnecter, itemQuitter );
        menuRapports.getItems().addAll(itemConsulter);
        menuPraticiens.getItems().addAll(itemHesitant);
        
        //ajout menu à la barre de menu
        barreMenu.getMenus().addAll(menuFichier,menuRapports, menuPraticiens);
      
        itemQuitter.setOnAction(actionEvent -> {
            confirmation();
        });
        
        itemSeConnecter.setOnAction(actionEvent -> {
        //fenêtre cd connexion
            Label labelTitle = new Label("Saisir vos donner de connexion.");
            Label matricule = new Label("matricule :");
            TextField fieldMatricule = new TextField();

            Label mdp = new Label("Mot de passe :");
            PasswordField champmdp = new  PasswordField();

            Button annule = new Button("Annuler");
            Button seConnecter = new Button("Se connecter");

            GridPane root = new GridPane();        

            root.add(labelTitle, 0, 0, 2, 1);
            root.setPadding(new Insets(20));
            root.setHgap(25);
            root.setVgap(15);

            GridPane.setHalignment(matricule, HPos.RIGHT);
           // Put on cell (0,1)
            root.add(matricule, 0, 1);

            GridPane.setHalignment(mdp, HPos.RIGHT);
            root.add(mdp, 0, 2);

            GridPane.setHalignment(fieldMatricule, HPos.LEFT);
            root.add(fieldMatricule, 1, 1);

            GridPane.setHalignment(champmdp, HPos.LEFT);
            root.add(champmdp, 1, 2);

            GridPane.setHalignment(seConnecter, HPos.RIGHT);
            root.add(seConnecter, 1, 3);

            GridPane.setHalignment(annule, HPos.RIGHT);
            root.add(annule, 2, 3);

            Scene secondScene = new Scene(root, 250, 100);

           // New window (Stage)
            Stage newWindow = new Stage();
            newWindow.setTitle("Authentification");
            newWindow.setScene(secondScene);
         });
        
        
        
        BorderPane border = new BorderPane();
        //position barre de menu
        border.setTop(barreMenu);
        
        Scene scene = new Scene(border, 500, 450);
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
