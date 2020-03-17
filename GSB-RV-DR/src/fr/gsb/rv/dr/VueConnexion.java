/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;


import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.SQLException;
import java.util.HashSet;
import javafx.scene.Scene;
import javafx.util.Pair;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import java.util.Optional;
import java.util.Set;
import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author developpeur
 */
public class VueConnexion extends Dialog<Pair<String,String>> {
    
    
    
    public VueConnexion(){
        Label labelMat = new Label("Matricule :");
        Label labelMdp = new Label("Mot de passe :");
        TextField entreeMatricule = new TextField();
        PasswordField entreeMdp = new PasswordField();
        
        
        this.setTitle("Connexion....");
        this.setHeaderText("Veuillez vous connecter");
        
        BorderPane root = new BorderPane();
        
        ButtonType loginButtonType = new ButtonType("Login", ButtonData.OK_DONE);
        this.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        

        grid.add(labelMat, 0, 0);
        grid.add(entreeMatricule, 1, 0);
        grid.add(labelMdp, 0, 1);
        grid.add(entreeMdp, 1, 1);
        
        this.getDialogPane().setContent(grid);
        
        
        setResultConverter(
            new Callback<ButtonType, Pair<String,String>>(){
                @Override
                public Pair<String,String> call(ButtonType typeBouton){
                    if(typeBouton == loginButtonType){
                        String matricule = entreeMatricule.getText().toString();
                        String mdp = entreeMdp.getText().toString();
                        return new Pair<String,String>(matricule,mdp);
                    }
                    return null;
                }
            }
        );
        
    }
    
    
        
        
        
    
}
