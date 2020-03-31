/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionBD.Mois;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
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
    private TableView tabRapports;
    
    public PanneauRapports() throws ConnexionException{
        
        List<Visiteur> lesVisiteurs = ModeleGsbRv.getVisiteurs();
        
        ObservableList<Visiteur>lstVisi = FXCollections.observableArrayList(lesVisiteurs);
        cbVisiteurs.setItems(lstVisi);
        
        GridPane root = new GridPane();
        root.getChildren().add(cbVisiteurs);
        
        
        
        
        this.setStyle("-fx-background-color: white;");
        
    }
    
    public void rafraichir(){
        
    }
    
}
