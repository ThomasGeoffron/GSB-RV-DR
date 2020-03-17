/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane {
    
    public PanneauRapports(){
        
        VBox colonne1 = new VBox();
        Label leLabel = new Label("Rapports");
        
        colonne1.getChildren().add(leLabel);
        this.getChildren().add(colonne1);
        
        this.setStyle("-fx-background-color: white;");
        
    }
    
}
