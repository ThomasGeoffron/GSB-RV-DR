/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr;

import fr.gsb.rv.dr.entites.RapportVisite;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author developpeur
 */
public class VueRapport extends Dialog {
    private Dialog dialogue = new Dialog();

    public VueRapport(RapportVisite leRapport){
        dialogue.setTitle("Rapport n°" + leRapport.getNumero());
        
        System.out.println(leRapport.getLeVisiteur().getNom());
        
        VBox vBoxPrincipale = new VBox();

        Label labelNum = new Label("Numéro : " + leRapport.getNumero());
        Label labelDateVisite = new Label("Date de visite : " + leRapport.getDateVisite());
        Label labelDateRedaction = new Label("Date de rédaction : " + leRapport.getDateRedaction());
        Label labelBilan = new Label("Bilan : " + leRapport.getBilan());
        Label labelMotif = new Label("Motif : " + leRapport.getMotif());
        Label labelCoefConfiance = new Label("Coefficient de confiance : " + leRapport.getCoefConfiance());
        Label labelLu = new Label("");
        if(leRapport.isLu()) {
            labelLu.setText("Non lu");
        }
        else {
            labelLu.setText("Lu");
        }
        Label labelVisiteur = new Label("Visiteur : " + leRapport.getLeVisiteur().getNom());
        Label labelPraticien = new Label("Praticien : " + leRapport.getLePraticien().getNom());
        Label labelCoefNotoriete = new Label("Coefficient de notoriété : " + leRapport.getLePraticien().getCoefNotoriete());

        vBoxPrincipale.getChildren().addAll(labelNum, labelDateVisite, labelDateRedaction, labelBilan,
                labelMotif, labelCoefConfiance, labelLu, labelVisiteur, labelPraticien, labelCoefNotoriete);

        ButtonType OK_DONE = ButtonType.CLOSE;

        dialogue.getDialogPane().getButtonTypes().add(OK_DONE);

        dialogue.getDialogPane().setContent(vBoxPrincipale);

    }

    public Dialog getDialog() {
        return dialogue;
    }
}
