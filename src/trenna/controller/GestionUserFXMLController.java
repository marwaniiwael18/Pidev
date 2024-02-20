/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import trenna.services.UserService;
import trenna.entities.User;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import trenna.entities.Role;
import trenna.services.RoleService;
import trenna.services.UserRoleService;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class GestionUserFXMLController implements Initializable {

    @FXML
    private ListView<User> list;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private ComboBox<Integer> age;
    @FXML
    private PasswordField mdp;
//    private ComboBox<?> role;
   
    final ObservableList rolee = FXCollections.observableArrayList();
    
    final ObservableList agee = FXCollections.observableArrayList(10,11,12,13,14,15,16,17,18,19,20
    ,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50);
    
    UserService userService = UserService.getInstance();
    
    @FXML
    private TextField email;
    @FXML
    private RadioButton admin;
    @FXML
    private RadioButton client;
    @FXML
    private TextField searchUser;
    @FXML
    private AnchorPane anchor;
    
   
    @FXML
    private Label labelUser;
    /*
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
//        anchor.setPrefWidth(852);
//        anchor.setPrefHeight(581);
//        anchor.resize(852, 581);
//        anchor.layout();
//        if(admin.isSelected()==true){
//            client.setSelected(false);
//        }else if(client.isSelected()==true){
//            admin.setSelected(false);
//        }
       
    
        age.setItems(agee);
        afficher();
        fill();   
        search();
    }        
//    private void initData(){
//            labelUser.setText(currentUser.getEmail());
//    }
    private void afficher() {
        List<User> users = userService.afficher();
        ObservableList<User> observableArrayListUser = 
        FXCollections.observableArrayList(users);
        list.setItems(observableArrayListUser);
        
    }   
    private void fill(){
        list.setOnMouseClicked(e->{
            User user = userService.rechercherParEmail(list.getSelectionModel().getSelectedItem());
            nom.setText(user.getNom());
            prenom.setText(user.getPrenom());
//            agee.add(user.getAge());
            age.setItems(agee);
            age.setPromptText(String.valueOf(user.getAge()));
            mdp.setText(user.getMdp());
            rolee.add(user.getRole().getName());
            if(user.getRole().getName().equals("ADMINISTRATOR")){
                admin.setSelected(true);
                client.setSelected(false);
            }else{
                client.setSelected(true);
                admin.setSelected(false);
            }
            email.setText(user.getEmail());
        });
    }
    private void search(){
        ObservableList<User> listUser = FXCollections.observableArrayList(userService.afficher());
        FilteredList<User> filteredData = new FilteredList<>(listUser,p -> true);

    //Set the filter Predicate whenever the filter changes.
    searchUser.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(user ->{
            // If filter text is empty, display all persons.
            if(newValue == null || newValue.isEmpty()){
                return true;
            }

            // Compare first name and last name of every client with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if(user.getPrenom().toLowerCase().contains(lowerCaseFilter)){
                return true; //filter matches first name
            }else if(user.getNom().toLowerCase().contains(lowerCaseFilter)){
                return true; //filter matches last name
            }else if(user.getEmail().toLowerCase().contains(lowerCaseFilter)){
                return true; //filter matches email
            }else if(String.valueOf(user.getAge()).toLowerCase().contains(lowerCaseFilter)){
                return true; //filter matches age
            }else if(String.valueOf(user.getRole()).toLowerCase().contains(lowerCaseFilter)){
                return true; //filter matches role
            }
            return false; //Does not match
        });
    });

    //Wrap the FilteredList in a SortedList.
        SortedList<User> sortedData = new SortedList<>(filteredData);

    //put the sorted list into the listview
    list.setItems(sortedData);
    }
    @FXML
    private void modifier(ActionEvent event) {
        User user = new User();
        User u = userService.rechercherParEmail(list.getSelectionModel().getSelectedItem());
        user.setId(u.getId());
        user.setEmail(u.getEmail());
        user.setNom(nom.getText());
        user.setPrenom(prenom.getText());
        user.setMdp(mdp.getText());
        user.setAge(age.getSelectionModel().getSelectedIndex()+10);
        Role r = new Role();
        RoleService serviceRole = new RoleService();
        if(admin.isSelected()){
            r = serviceRole.getById(1);
            client.setSelected(false);
        }else if(client.isSelected()){
             r = serviceRole.getById(2);
            admin.setSelected(false);
        }
        user.setRole(r);
        userService.update(user);
        afficher();
    }

    @FXML
    private void Supprimer(ActionEvent event) {
        User u = new User();
        UserRoleService  userRoleService = new UserRoleService();
        u.setEmail(email.getText());
        User user = userService.rechercherParEmail(u);
        userRoleService.getById(user.getId());
        userService.supprimer(user);
        afficher();
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trenna/fxml/LoginFXML.fxml"));
        Parent root = loader.load();            
        LoginFXMLController ac =loader.getController();
        email.getScene().setRoot(root);
    }
    
}
