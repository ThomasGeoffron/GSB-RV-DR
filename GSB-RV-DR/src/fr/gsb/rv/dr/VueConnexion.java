/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import javafx.util.Pair;

/**
 *
 * @author developpeur
 */
public class VueConnexion extends Dialog {
    
    private Dialog<Pair<String,String>> boite = new Dialog<>();
    
    public VueConnexion() {
        
        boite.setTitle("Authentification");
        boite.setHeaderText("Saisir vos données de connexion.");
        
        GridPane grid = new GridPane();
        
        Label lblMatricule = new Label("Matricule : ");
        Label lblMdp = new Label("Mot de passe : ");
        
        TextField insMatricule = new TextField();
        PasswordField insMdp = new PasswordField();
        
        grid.add(lblMatricule, 0, 0);
        grid.add(insMatricule, 1, 0);
        grid.add(lblMdp, 0, 1);
        grid.add(insMdp, 1, 1);
        
        boite.getDialogPane().setContent(grid);
        
        ButtonType OK_DONE = new ButtonType("Se connecter");
        ButtonType CANCEL_CLOSE = new ButtonType("Annuler");
        
        boite.getDialogPane().getButtonTypes().addAll(OK_DONE, CANCEL_CLOSE);
        
        boite.setResultConverter((ButtonType typeBouton) -> {
            if ( typeBouton == OK_DONE ) {
                return new Pair<String, String>( insMatricule.getText(), insMdp.getText() );
            }
            else if(typeBouton == CANCEL_CLOSE){
                insMatricule.setText("");
                insMdp.setText("");
                boite.close();
            }
            return null;
        });
    }
    
    public Dialog<Pair<String, String>> getDialog() {
        return boite;
    }
        
}
