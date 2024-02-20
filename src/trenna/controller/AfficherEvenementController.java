/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import static trenna.controller.ListEvenementController.selectedItem5;
import trenna.entities.Commentaire;
import trenna.entities.evenement;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import trenna.services.CommentaireService;
import trenna.services.evenementservice;
 
/**
 * FXML Controller class
 *
 * @author medom
 */
public class AfficherEvenementController implements Initializable {
   
    static evenement selectedItem ;
    static Commentaire selectedItem6;

    private TextField list;
    private TextField id;
    @FXML
    private Button supprimerbtn;
    @FXML
    private ListView<Commentaire> listcom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CommentaireService cs= new CommentaireService();
        listcom.getItems().addAll(cs.readAll());
    }    
    
   void selList (String list){
        this.list.setText(list);
    }
    
   
   
    

   

    private void AjouterEvenement(ActionEvent event) {
         try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvenement.fxml"));
            Parent root = loader.load();
            AjouterEvenementController ac =loader.getController();
            
            
            id.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    

    @FXML
    private void index3(MouseEvent event) {
                selectedItem6 = listcom.getSelectionModel().getSelectedItem();
        
        
    }

    @FXML
    private void SupprimerCommentaire(ActionEvent event) {
        Commentaire ev =new Commentaire();
        CommentaireService es =new CommentaireService();
        es.supprimer(selectedItem6.getId());
    }

    }
    

