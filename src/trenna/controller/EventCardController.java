/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import trenna.entities.evenement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import trenna.fxml.MyListener;

/**
 * FXML Controller class
 *
 * @author medom
 */
public class EventCardController  {

    @FXML
    private Label nom;
    @FXML
    private Label dateevenement;
    @FXML
    private Label prrix;
    @FXML
    private Label recompense;
    
    @FXML
    private void click(MouseEvent mouseEvent) {
        myListener.onClickListener(e);
    }

    /**
     * Initializes the controller class.
     */
   
    private evenement e;
    private MyListener myListener;
    
    
    public void setData(evenement e, MyListener myListener)
   {  this.e = e;
      this.myListener = myListener;
      
       nom.setText(e.getNom());
       dateevenement.setText(String.valueOf(e.getDateEven()));
       prrix.setText(String.valueOf(e.getPrix()));
       recompense.setText(String.valueOf(e.getRecompense()));
       
       
       
            
               
   }
    
}
