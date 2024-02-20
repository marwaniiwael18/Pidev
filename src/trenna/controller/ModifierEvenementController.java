/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import trenna.entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import trenna.services.evenementservice;

/**
 * FXML Controller class
 *
 * @author medom
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private TextField idevenement;
    @FXML
    private TextField recompense;
    @FXML
    private TextField nom;
    @FXML
    private TextField prix;
    @FXML
    private Button jouter;
    @FXML
    private Button supprimerbtn;
    @FXML
    private ListView<?> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   
    

    @FXML
    private void modifierevenement(ActionEvent event) {
        
         evenement ev = new evenement();
        evenementservice es = new evenementservice();
         ev.setIdEven(Integer.parseInt(idevenement.getText()));
         ev.setNom(nom.getText());
         ev.setDateEven(new Date(2022, 3, 30));
         ev.setPrix(Integer.parseInt(prix.getText()));
         ev.setRecompense(Integer.parseInt(recompense.getText()));
         es.modifier(ev);
        
        
    }


    @FXML
    private void AjouterEvenement(ActionEvent event)  {
      try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            Parent root = loader.load();
            AjouterEvenementController ac =loader.getController();
            
            
            nom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void AfficherEvenement(ActionEvent event) {
        try {
             evenement ev = new evenement();
         evenementservice es = new evenementservice();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = loader.load();
            AfficherEvenementController ac =loader.getController();
            ac.selList(es.readAll().toString());
            
            
            nom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @FXML
    private void SupprimerEvenement(ActionEvent event) {
        try {
             evenement ev = new evenement();
        evenementservice es = new evenementservice();
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvenement.fxml"));
            Parent root = loader.load();
            AfficherEvenementController ac =loader.getController();
            ac.selList(es.readAll().toString());
            
            
            nom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
    
}

   
}
