/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import java.io.IOException;
import java.net.URL;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class SearchController implements Initializable {

    ArrayList<String> words = new ArrayList<>(
            Arrays.asList("foot","hand","basket","judo","karaté","volley","tennis")
            
            
            );
    
    
    @FXML
    private ListView<String> list;
    @FXML
    private TextField rech;
    @FXML
    private Button rtr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        list.getItems().addAll(words);
        
    }   
    
    private List<String> rech(String searchWords, List<String> listOfStrings){
       List<String> searchWordsArray = Arrays.asList(searchWords.trim());
       return listOfStrings.stream().filter(input -> {
       return searchWordsArray.stream().allMatch(word ->
       input.toLowerCase().contains(word.toLowerCase()));
       }).collect(Collectors.toList());
    }

    @FXML
    private void search(ActionEvent event) {
       list.getItems().clear();
       list.getItems().addAll(rech(rech.getText(),words));
    }

    @FXML
    private void rtr(ActionEvent event) throws IOException{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/arbitre.fxml"));
                Parent root = loader.load();
                ArbitreController aa = loader.getController();
                rtr.getScene().setRoot(root);
    }
     
}
//SELECT * FROM match