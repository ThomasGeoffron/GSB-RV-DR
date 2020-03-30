/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import java.awt.LayoutManager;
import java.awt.Panel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane {
    
    private ComboBox<Visiteur> cbVisiteurs;
    private ComboBox<Mois> cbMois;
    private ComboBox<Integer> cbAnnee;
    
    private TableView<RapportVisite> tabRapports;
    
    private Pane pane = new Pane();
    
    public PanneauRapports() {
        VBox grid = new VBox();
        
        Label lblRapports = new Label("Rapports");
        
        grid.getChildren().add(lblRapports);
        
        grid.setStyle("-fx-background-color : white");
        
        pane.getChildren().add(grid);
    }

    public Pane getPane() {
        return pane;
    }

    public void rafraichir(){
        
    }
    
}
