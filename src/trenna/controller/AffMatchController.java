/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import trenna.entities.Arbitre;

import trenna.entities.Match;
import trenna.entities.Pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import trenna.services.ServiceMatch;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class AffMatchController implements Initializable {

    @FXML
    private TableColumn<Match, String> idMatchh;
    @FXML
    private TableColumn<Match, String> typeMatchh;
    @FXML
    private TableColumn<Match, String> dateMatchh;
    @FXML
    private TableColumn<Match, String> idArb;
    @FXML
    private TableView<Match> affiche;
    @FXML
    private TextField rchh;
    @FXML
    private Button az;
    @FXML
    private Button pdf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ServiceMatch us = new ServiceMatch();
        
        List<Match> l = new ArrayList<>();
        
        l = (ArrayList<Match>) us.afficher();
        ObservableList<Match> data = FXCollections.observableArrayList(l);
        FilteredList<Match> fle = new FilteredList(data, e -> true);
        idMatchh.setCellValueFactory(new PropertyValueFactory<>("idMatch"));
        typeMatchh.setCellValueFactory(new PropertyValueFactory<>("typeMatch"));
        dateMatchh.setCellValueFactory(new PropertyValueFactory<>("dateMatch"));
        idArb.setCellValueFactory(new PropertyValueFactory<>("idArb"));
      
        affiche.setItems(fle);
        int nbe=affiche.getItems().size();
        
        
    }    

    @FXML
    private void aff(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/search.fxml"));
                Parent root = loader.load();
                SearchController aa = loader.getController();
                rchh.getScene().setRoot(root);
    }

    
    

    @FXML
    private void rt(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/arbitre.fxml"));
                Parent root = loader.load();
                ArbitreController aa = loader.getController();
                az.getScene().setRoot(root); 
    }
     
    @FXML
    private void pdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("list of Match");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceMatch.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
        
    }


   


  
    

