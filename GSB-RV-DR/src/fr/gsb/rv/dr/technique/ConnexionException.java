/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gsb.rv.dr.technique;
import java.lang.Exception;
/**
 *
 * @author developpeur
 */
public class ConnexionException extends Exception{
    
    public String getMessage(){
        return "Erreur de connexion BD" ;
    }
}
