/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
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
    
    private TableView<RapportVisite> tabRapports;
    
    private Pane pane = new Pane();
    
    public PanneauRapports() {
        try {
           VBox grid = new VBox();
        
            ObservableList<Visiteur> lesVisiteurs = FXCollections.observableArrayList(ModeleGsbRv.getVisiteurs());

            for (Visiteur unVisiteur : lesVisiteurs) {

                cbVisiteurs.getItems().add(unVisiteur);
            }

            for (Mois unMois : Mois.values()) {
                cbMois.getItems().add(unMois);
            }

            int aujourdhui = LocalDate.now().getYear();

            int depart = aujourdhui - 5;

            while (depart <= aujourdhui) {
                cbAnnee.getItems().add(depart);

                depart += 1;
            }

            HBox combos = new HBox();
            
            combos.getChildren().add(cbVisiteurs);
            combos.getChildren().add(cbMois);
            combos.getChildren().add(cbAnnee);

            Button bouton = new Button("Valider");

            bouton.setOnAction((ActionEvent event) -> {
                rafraichir();
            });

            grid.getChildren().add(combos);
            grid.getChildren().add(bouton);

            grid.setStyle("-fx-background-color : white");

            pane.getChildren().add(grid); 
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Pane getPane() {
        return pane;
    }

    public void rafraichir(){
        System.out.println(cbVisiteurs.getSelectionModel().getSelectedItem()
                    + " " + cbMois.getSelectionModel().getSelectedItem()
                    + " " + cbAnnee.getSelectionModel().getSelectedItem());
    }
    
}
