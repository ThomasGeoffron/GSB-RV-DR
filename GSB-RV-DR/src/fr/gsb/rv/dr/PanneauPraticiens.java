/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class PanneauPraticiens extends Pane {
    public static int CRITERE_COEF_CONFIANCE = 1;
    public static int CRITERE_COEF_NOTORIETE = 2;
    public static int CRITERE_DATE_VISITE = 3;
    
    private int critereTri = CRITERE_COEF_CONFIANCE;
    private RadioButton rbCoefConfiance;
    private RadioButton rbCoefNotoriete;
    private RadioButton rbDateVisite;
    private ToggleGroup groupeCriteres = new ToggleGroup();
    
    public PanneauPraticiens(List<Praticien> praticiens){
        
        GridPane root = new GridPane();
        
        this.rbCoefConfiance = new RadioButton();
        rbCoefConfiance.setText("Confiance");
        rbCoefConfiance.setToggleGroup(groupeCriteres);
        rbCoefConfiance.setSelected(true);
        
        this.rbCoefNotoriete = new RadioButton();
        rbCoefNotoriete.setText("Notoriété");
        rbCoefNotoriete.setToggleGroup(groupeCriteres);
        
        this.rbDateVisite = new RadioButton();
        rbDateVisite.setText("Date Visite");
        rbDateVisite.setToggleGroup(groupeCriteres);
        
        
        
        TableView<Praticien> tableau = new TableView<Praticien>();
        
        TableColumn<Praticien, String> colNumero //
              = new TableColumn<Praticien, String>("Numéro");
        
        TableColumn<Praticien, String> colNom //
              = new TableColumn<Praticien, String>("Nom");
        
        TableColumn<Praticien, String> colVille //
              = new TableColumn<Praticien, String>("Ville");
        
        TableColumn<Praticien, String> colCoefNotoriete //
              = new TableColumn<Praticien, String>("CoefNotoriete");
        
        TableColumn<Praticien, String> colDateDerniereVisite //
              = new TableColumn<Praticien, String>("DateDerniereVisite");
        
        TableColumn<Praticien, String> colDernierCoefConfiance //
              = new TableColumn<Praticien, String>("DernierCoefConfiance");
        
        
        
        tableau.getColumns().addAll(colNumero, colNom, colVille, colCoefNotoriete,colDateDerniereVisite,colDernierCoefConfiance );    
        
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        colCoefNotoriete.setCellValueFactory(new PropertyValueFactory<>("coefNotoriete"));
        colDateDerniereVisite.setCellValueFactory(new PropertyValueFactory<>("dateDerniereVisite"));
        colDernierCoefConfiance.setCellValueFactory(new PropertyValueFactory<>("dernierCoefConfiance"));
        
        if(critereTri == CRITERE_COEF_CONFIANCE){
            Collections.sort(praticiens, new ComparateurCoefConfiance() );
        }
        else if(critereTri == CRITERE_COEF_NOTORIETE){
            Collections.sort(praticiens, new ComparateurCoefNotoriete() );
        }
        
        else{
            Collections.sort(praticiens, new ComparateurDateVisite());
        }
        
        rbCoefConfiance.setOnAction((ActionEvent actionEvent) ->{
            this.rafraichir();
        });
        
        rbCoefNotoriete.setOnAction((ActionEvent actionEvent) ->{
            this.rafraichir();
        });
        
        rbDateVisite.setOnAction((ActionEvent actionEvent)->{
            this.rafraichir();
        });
        
        ObservableList<Praticien> list = FXCollections.observableArrayList(praticiens);;
        tableau.setItems(list);
        
        Label leLabel = new Label("Sélectionnez un critère de tri :");
        
        root.add(leLabel, 0,0,5,1);
        root.add(rbCoefConfiance, 0,1,3,1);
        root.add(rbCoefNotoriete, 3,1,3,1);
        root.add(rbDateVisite, 6,1,3,1);
        
        root.add(tableau, 0,3,10,100);
        this.getChildren().add(root);
        //this.getChildren().add(tableau);
        this.setStyle("-fx-background-color: white;");
        
    }
    
    public void rafraichir(){
        if(rbCoefConfiance.isSelected()){
            this.setCritereDeTri(CRITERE_COEF_CONFIANCE);
            System.out.println("action1");
        }
        else if(rbCoefNotoriete.isSelected()){
            this.setCritereDeTri(CRITERE_COEF_NOTORIETE);
            System.out.println("action2");
        }
        else{
            this.setCritereDeTri(CRITERE_DATE_VISITE);
            System.out.println("action3");
        }
    }
    
    public void setCritereDeTri(int critere){
        this.critereTri = critere;
    }
}
