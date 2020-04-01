/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
    private TableView<RapportVisite> tabRapports = new TableView();
    private Pane root = new Pane();
   
    
    public PanneauRapports() throws ConnexionException{
        
        VBox vBoxPrincipal = new VBox();
        HBox hBoxComboBox = new HBox();
        HBox hBoxBoutonValider = new HBox();
        
        List<Visiteur> lesVisiteurs = ModeleGsbRv.getVisiteurs();
        
        ObservableList<Visiteur>oListVisi = FXCollections.observableArrayList(lesVisiteurs);
        
        for(Visiteur unVisi : oListVisi){
            cbVisiteurs.getItems().add(unVisi);
        }
       
        for(Mois unMois: Mois.values()){
            cbMois.getItems().add(unMois);
        }
        
        
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        
        for(int i = 0; i<=5 ; i++){
            cbAnnee.getItems().add(currentYear - i);
        }
        
        Button valider = new Button("Valider");
        valider.setOnAction((ActionEvent actionEvent)->{
            if(cbVisiteurs.getSelectionModel().isEmpty() == false){
                if(cbMois.getSelectionModel().isEmpty() == false){
                    if(cbAnnee.getSelectionModel().isEmpty() == false){
                        try {   
                            this.rafraichir();
                        } catch (ConnexionException ex) {
                            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    }
                    else{
                        System.out.println("L'année n'a pas été choisie");
                    }
                }
                else{
                    System.out.println("Le mois n'a pas été choisi");
                }
                
            }
            else{
                System.out.println("Le visiteur n'a pas été choisi");
            }
            
            
        });
        
        
        TableColumn<RapportVisite,Integer> colNum =
                new TableColumn<RapportVisite,Integer>("Numéro");
        TableColumn<RapportVisite,String> colNom =
                new TableColumn<RapportVisite,String>("Praticien");
        TableColumn<RapportVisite,String> colVille =
                new TableColumn<RapportVisite,String>("Ville");
        TableColumn<RapportVisite,LocalDate> colVisite =
                new TableColumn<RapportVisite,LocalDate>("Date Visite");
        TableColumn<RapportVisite,LocalDate> colRedac =
                new TableColumn<RapportVisite,LocalDate>("Date Rédaction");
        
        colNum.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
        colRedac.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));


        colNom.setCellValueFactory((CellDataFeatures<RapportVisite,String> param) -> {
            String nom = param.getValue().getLePraticien().getNom();

            return new SimpleStringProperty(nom);
        });


        colVille.setCellValueFactory((CellDataFeatures<RapportVisite, String> param) -> {
            String ville = param.getValue().getLePraticien().getVille();
            return new SimpleStringProperty(ville);
        });

        colVisite.setCellFactory(colonne -> {
                
            return new TableCell<RapportVisite,LocalDate>(){
                    @Override
                    protected void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText("");
                        }
                        else {
                            DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            setText(item.format(formateur));
                        }
                    }
                };
            }
        );

        colRedac.setCellFactory(colonne -> {
                
                    return new TableCell<RapportVisite,LocalDate>(){
                        @Override
                        protected void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty) {
                                setText("");
                            }
                            else {
                                DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                setText(item.format(formateur));
                            }
                        }
                    };
                }
        );

        tabRapports.setRowFactory( ligne -> {
               
                return new TableRow<RapportVisite>(){
                        @Override
                        protected void updateItem(RapportVisite item, boolean empty) {
                            super.updateItem(item, empty);

                            if(item != null) {
                                if(item.isLu()) {
                                    setStyle( "-fx-background-color: lightblue");
                                }
                                else {
                                    setStyle( "-fx-background-color: white");
                                }
                            }
                         }
                    };
                }
        );
        tabRapports.setRowFactory(ligne -> {
                    
                return new TableRow<RapportVisite>(){
                        @Override
                        protected void updateItem(RapportVisite item, boolean empty) {
                            super.updateItem(item, empty);
                            if(item != null) {
                                if(item.isLu()) {
                                    setStyle( "-fx-background-color: lightblue");
                                }
                                else {
                                    setStyle( "-fx-background-color: white");
                                }
                            }
                        }
                    };
                }
        );

        tabRapports.setOnMouseClicked((MouseEvent event) -> {
                
                    if( event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                        //int indiceRapport = tabRapports;
                        RapportVisite rapport = tabRapports.getSelectionModel().getSelectedItem();
                        
                        try {
                            ModeleGsbRv.setRapportVisiteLu(cbVisiteurs.getSelectionModel().getSelectedItem().getMatricule(), rapport.getNumero());
                            
                            VueRapport laVue = new VueRapport(rapport);
                            Dialog vueRapport = laVue.getDialog();
                            vueRapport.showAndWait();
                            
                            rafraichir();
                        } catch (ConnexionException ex) {
                            Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    }
                }
        );

        tabRapports.getColumns().addAll(colNum, colNom, colVille, colVisite, colRedac);
        
        
        hBoxComboBox.getChildren().add(cbVisiteurs);
        hBoxComboBox.getChildren().add(cbMois);
        hBoxComboBox.getChildren().add(cbAnnee);
        
        hBoxBoutonValider.getChildren().add(valider);
        
        vBoxPrincipal.getChildren().add(hBoxComboBox);
        vBoxPrincipal.getChildren().add(hBoxBoutonValider);
        vBoxPrincipal.getChildren().add(tabRapports);
        
        root.getChildren().add(vBoxPrincipal);
        
        
        this.getChildren().add(root);
        this.setStyle("-fx-background-color: white;");
        
    }
    
    public void rafraichir() throws ConnexionException{
        /*
        System.out.println(cbVisiteurs.getSelectionModel().getSelectedItem().toString()+
                                " " + cbMois.getSelectionModel().getSelectedItem()+
                                " " + cbAnnee.getSelectionModel().getSelectedItem());
        */
        
        String matricule = cbVisiteurs.getSelectionModel().getSelectedItem().getMatricule();
        int mois = cbMois.getSelectionModel().getSelectedItem().ordinal() + 1;
        int annee = cbAnnee.getSelectionModel().getSelectedItem();
        
        
        
        List<RapportVisite> liste = ModeleGsbRv.getRapportsVisite(matricule, mois, annee);
        
        
        ObservableList<RapportVisite> listeRapports = FXCollections.observableArrayList(liste);
        
        tabRapports.setItems(listeRapports);
    }
    
}
