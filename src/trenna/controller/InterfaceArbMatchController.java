/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class InterfaceArbMatchController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Label dt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  dt.startFullDrag();
    }    

    @FXML
    private void intrmatch(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/arbitre.fxml"));
                Parent root = loader.load();
                ArbitreController af = loader.getController();
                nom.getScene().setRoot(root);
    }

    @FXML
    private void intarb(ActionEvent event) throws IOException {
       
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/match.fxml"));
                Parent root = loader.load();
                MatchController aa = loader.getController();
                nom.getScene().setRoot(root);
    }
/*
    @FXML
    private void timer(ActionEvent event) {
        DateTime dateTime = DateTime.Now;
        this.dt.Text = dateTime.ToString();
    }*/
    
    
    
}
