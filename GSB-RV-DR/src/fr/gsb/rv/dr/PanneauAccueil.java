/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import java.awt.LayoutManager;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauAccueil extends Pane {
    
    private Pane pane = new Pane();
    
    public PanneauAccueil() {
        VBox grid = new VBox();
        
        Label lblAccueil = new Label("Accueil");
        
        grid.getChildren().add(lblAccueil);
        
        grid.setStyle("-fx-background-color : white");
        
        pane.getChildren().add(grid);
    }
    
    public Pane getPane() {
        return pane;
    }
       
}
