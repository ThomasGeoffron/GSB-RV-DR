/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.RapportVisite;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 *
 * @author developpeur
 */
public class VueRapport extends Dialog {
    
    private Dialog boite = new Dialog();
    
    public VueRapport(RapportVisite rapport){
        boite.setTitle("Rapport n°" + rapport.getNumero());
        
        VBox grid = new VBox();
        
        Label lblNum = new Label("Numéro : " + rapport.getNumero());
        Label lblDateVisite = new Label("Date de visite : " + rapport.getDateVisite());
        Label lblDateRedaction = new Label("Date de redaction : " + rapport.getDateRedaction());
        Label lblBilan = new Label("Bilan : " + rapport.getBilan());
        Label lblMotif = new Label("Motif : " + rapport.getMotif());
        Label lblCoefConfiance = new Label("Coefficient de confiance : " + rapport.getCoefConfiance());
        Label lblLu = new Label("");
        if(rapport.isLu()) {
            lblLu.setText("A été lu : Oui");
        }
        else {
            lblLu.setText("A été lu : Non");
        }
        Label lblVisiteur = new Label("Visiteur : " + rapport.getLeVisiteur().getNom());
        Label lblPraticien = new Label("Praticien : " + rapport.getLePraticien().getNom());
        Label lblCoefNotoriete = new Label("Coefficient de notoriété : " + rapport.getLePraticien().getCoefNotoriete());
        
        grid.getChildren().addAll(lblNum, lblDateVisite, lblDateRedaction, lblBilan,
                lblMotif, lblCoefConfiance, lblLu, lblVisiteur, lblPraticien, lblCoefNotoriete);
        
        ButtonType OK_DONE = ButtonType.CLOSE;
        
        boite.getDialogPane().getButtonTypes().add(OK_DONE);
                
        boite.getDialogPane().setContent(grid);
        
    }
    
    public Dialog getDialog() {
        return boite;
    }
    
}
