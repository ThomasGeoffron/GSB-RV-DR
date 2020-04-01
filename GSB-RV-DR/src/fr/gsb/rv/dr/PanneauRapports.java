/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane {
    
    private ComboBox<Visiteur> cbVisiteurs = new ComboBox();
    private ComboBox<Mois> cbMois = new ComboBox();
    private ComboBox<Integer> cbAnnee = new ComboBox();
    private TableView tabRapports;
    private Pane root = new Pane();
   
    
    public PanneauRapports() throws ConnexionException{
        
        VBox vBoxPrincipal = new VBox();
        HBox hBoxComboBox = new HBox();
        
        List<Visiteur> lesVisiteurs = ModeleGsbRv.getVisiteurs();
        
        ObservableList<Visiteur>oListVisi = FXCollections.observableArrayList(lesVisiteurs);
        
        for(Visiteur unVisi : oListVisi){
            cbVisiteurs.getItems().add(unVisi);
        }
        cbVisiteurs.setValue(cbVisiteurs.getItems().get(0));
        
        cbVisiteurs.setOnAction((ActionEvent actionEvent) ->{
            Visiteur leVisiteurSelectionne = cbVisiteurs.getSelectionModel().getSelectedItem();
            System.out.println(leVisiteurSelectionne.toString());
        });
        
        
        for(Mois unMois: Mois.values()){
            cbMois.getItems().add(unMois);
        }
        cbMois.setValue(cbMois.getItems().get(0));
        
        hBoxComboBox.getChildren().add(cbVisiteurs);
        hBoxComboBox.getChildren().add(cbMois);
        
        vBoxPrincipal.getChildren().add(hBoxComboBox);
        
        root.getChildren().add(vBoxPrincipal);
        
        
        this.getChildren().add(root);
        this.setStyle("-fx-background-color: white;");
        
    }
    
    public void rafraichir(){
        
    }
    
}
