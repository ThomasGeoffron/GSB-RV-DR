/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.entites;

import java.time.LocalDate;

/**
 *
 * @author developpeur
 */
public class Praticien {
    private int numero;
    private String nom;
    private String ville;
    private float coefNotoriete;
    private LocalDate dateDerniereVisite;
    private int dernierCoefConfiance;
    
    public Praticien(){};
    
    public Praticien(int numero, String nom, String ville, float coefNotoriete, LocalDate dateDerniereVisite, int dernierCoefConfiance) {
        this.numero = numero;
        this.nom = nom;
        this.ville = ville;
        this.coefNotoriete = coefNotoriete;
        this.dateDerniereVisite = dateDerniereVisite;
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    public int getNumero() {
        return numero;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public float getCoefNotoriete() {
        return coefNotoriete;
    }

    public LocalDate getDateDerniereVisite() {
        return dateDerniereVisite;
    }

    public int getDernierCoefConfiance() {
        return dernierCoefConfiance;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCoefNotoriete(float coefNotoriete) {
        this.coefNotoriete = coefNotoriete;
    }

    public void setDateDerniereVisite(LocalDate dateDerniereVisite) {
        this.dateDerniereVisite = dateDerniereVisite;
    }

    public void setDernierCoefConfiance(int dernierCoefConfiance) {
        this.dernierCoefConfiance = dernierCoefConfiance;
    }

    @Override
    public String toString() {
        return "Praticien{" + "numero=" + numero + ", nom=" + nom + ", ville=" + ville + ", coefNotoriete=" + coefNotoriete + ", dateDerniereVisite=" + dateDerniereVisite + ", dernierCoefConfiance=" + dernierCoefConfiance + '}';
    }
    
    
    
}
