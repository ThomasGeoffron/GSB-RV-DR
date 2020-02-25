/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        MenuBar barreMenus = new MenuBar();
           
        Menu menuFichier = new Menu("Fichier");
        MenuItem itemSeConnecter = new MenuItem("Se connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se déconnecter");
        
        SeparatorMenuItem separator = new SeparatorMenuItem();
           
        MenuItem itemQuitter = new MenuItem("Quitter");
        itemQuitter.setAccelerator(KeyCombination.keyCombination("Ctrl + X"));
        
        itemQuitter.setOnAction( actionEvent ->  {
                Platform.exit();
            }
        );
        

        menuFichier.getItems().addAll(itemSeConnecter, itemSeDeconnecter, 
                separator, itemQuitter);
        
        barreMenus.getMenus().add(menuFichier);
        
        root.setTop(barreMenus);
        
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
