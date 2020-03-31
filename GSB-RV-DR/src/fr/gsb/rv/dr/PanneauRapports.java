/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author developpeur
 */
public class PanneauRapports extends Pane {
    
    private ComboBox<Visiteur> cbVisiteurs = new ComboBox();
    private ComboBox<Mois> cbMois = new ComboBox();
    private ComboBox<Integer> cbAnnee = new ComboBox();
    
    private TableView<RapportVisite> tabRapports = new TableView();
    
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
            
            Button bouton = new Button("Valider");

            bouton.setOnAction((ActionEvent event) -> {
               try {
                   rafraichir();
               } catch (ConnexionException | SQLException ex) {
                   Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
               }
            });
                        
            TableColumn<RapportVisite,Integer> colNumero = new TableColumn<RapportVisite,Integer>("Numéro");
            TableColumn<RapportVisite,String> colNom = new TableColumn<RapportVisite,String>("Praticien");
            TableColumn<RapportVisite,String> colVille = new TableColumn<RapportVisite,String>("Ville");
            TableColumn<RapportVisite,LocalDate> colVisite = new TableColumn<RapportVisite,LocalDate>("Visite");
            TableColumn<RapportVisite,LocalDate> colRedaction = new TableColumn<RapportVisite,LocalDate>("Rédaction");
            
            colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            colVisite.setCellValueFactory(new PropertyValueFactory<>("dateVisite"));
            colRedaction.setCellValueFactory(new PropertyValueFactory<>("dateRedaction"));
            
            
            colNom.setCellValueFactory((CellDataFeatures<RapportVisite,String> param) -> {
                String nom = param.getValue().getLePraticien().getNom();
                                
                return new SimpleStringProperty(nom);
           });
            
            
            colVille.setCellValueFactory((CellDataFeatures<RapportVisite, String> param) -> {
                String ville = param.getValue().getLePraticien().getVille();
                
                return new SimpleStringProperty(ville);
            });
            
            colVisite.setCellFactory(
                    colonne -> {
                        return new TableCell<RapportVisite,LocalDate>(){
                            @Override
                            protected void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                
                                if (empty) {
                                    setText("");
                                }
                                else {
                                    DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                    setText(item.format(formateur));
                                }
                            }
                        };
                    }
            );
            
            colRedaction.setCellFactory(
                    colonne -> {
                        return new TableCell<RapportVisite,LocalDate>(){
                            @Override
                            protected void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                
                                if (empty) {
                                    setText("");
                                }
                                else {
                                    DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                    setText(item.format(formateur));
                                }
                            }
                        };
                    }
            );
            
            tabRapports.setRowFactory(
                    ligne -> {
                        return new TableRow<RapportVisite>(){
                            @Override
                            protected void updateItem(RapportVisite item, boolean empty) {
                                super.updateItem(item, empty);

                                if(item != null) {
                                    if(item.isLu()) {
                                        setStyle( "-fx-background-color: gold");
                                    }
                                    else {
                                        setStyle( "-fx-background-color: cyan");
                                    }
                                }
                                
                            }
                        };
                    }
            );
            
            tabRapports.setOnMouseClicked(
                    (MouseEvent event) -> {
                        if( event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                            int indiceRapport = tabRapports.getSelectionModel().getSelectedIndex();
                            RapportVisite rapport = tabRapports.getSelectionModel().getSelectedItem();
                            
                            try {
                                ModeleGsbRv.setRapportVisiteLu(cbVisiteurs.getSelectionModel().getSelectedItem().getMatricule(), rapport.getNumero());
                                
                                VueRapport laVue = new VueRapport(rapport);
                                Dialog vueRapport = laVue.getDialog();
                                vueRapport.showAndWait();
                                
                                rafraichir();
                            } catch (ConnexionException | SQLException ex) {
                                Logger.getLogger(PanneauRapports.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            
                        }
                    }
            );
            
            tabRapports.getColumns().addAll(colNumero, colNom, colVille, colVisite, colRedaction);
            
            HBox combos = new HBox();
            
            combos.getChildren().add(cbVisiteurs);
            combos.getChildren().add(cbMois);
            combos.getChildren().add(cbAnnee);

            grid.getChildren().add(combos);
            grid.getChildren().add(bouton);
            grid.getChildren().add(tabRapports);

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

    public void rafraichir() throws ConnexionException, SQLException{
//        System.out.println(cbVisiteurs.getSelectionModel().getSelectedItem()
//                    + " " + cbMois.getSelectionModel().getSelectedItem()
//                    + " " + cbAnnee.getSelectionModel().getSelectedItem());

        String matricule = cbVisiteurs.getSelectionModel().getSelectedItem().getMatricule();
        int mois = cbMois.getSelectionModel().getSelectedItem().ordinal() + 1;
        int annee = cbAnnee.getSelectionModel().getSelectedItem();

        List<RapportVisite> liste = ModeleGsbRv.getRapportsVisite(matricule, mois, annee);
        
        ObservableList<RapportVisite> listeRapports = FXCollections.observableArrayList(liste);
        
        tabRapports.setItems(listeRapports);
    }
    
}
