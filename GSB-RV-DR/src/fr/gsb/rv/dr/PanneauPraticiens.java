/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import java.awt.LayoutManager;
import java.awt.Panel;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauPraticiens extends Pane {
    
    private Pane pane = new Pane();
    
    public PanneauPraticiens() {
        VBox grid = new VBox();
        
        Label lblPraticiens = new Label("Praticiens");
        
        grid.getChildren().add(lblPraticiens);
        
        grid.setStyle("-fx-background-color = white");
        
        pane.getChildren().add(grid);
    }
    
    public Pane getPane() {
        return pane;
    }
    
}
