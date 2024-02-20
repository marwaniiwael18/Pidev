/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import trenna.common.RegexValidation;
import trenna.entities.Role;
import trenna.entities.User;
import trenna.entities.UserRole;
import trenna.exceptions.BadRequestException;
import trenna.services.UserService;
import trenna.utils.JavaMailUtils;

/**
 * FXML Controller class
 *
 * @author moham
 */
public class UserFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField age;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField mdp;
    @FXML
    private ComboBox<?> agee;
 
    final ObservableList agees = FXCollections.observableArrayList(10,11,12,13,14,15,16,17,18,19,20
    ,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     agee.setItems(agees);
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws IOException, Exception {
        User user = new User();
        Role role = new Role();
        role.setIdRole(1);
        UserService userService = UserService.getInstance();
        user.setNom(nom.getText());
        user.setPrenom(prenom.getText());
        
        user.setEmail(email.getText());
        
        user.setMdp(mdp.getText());
        user.setRole(role);
//        User u = userService.rechercherParEmail(user);
        
        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (null != mdp.getText() && (mdp.getText().length()) < 8
	        || !RegexValidation.checkPasswordFormat(RegexValidation.CHAR_PATTERN, mdp.getText())
		|| !RegexValidation.checkPasswordFormat(RegexValidation.NUMERIC_PATTERN, mdp.getText())
		|| !RegexValidation.checkPasswordFormat(RegexValidation.NOT_CHAR_PATTERN, mdp.getText())) {
           
            alert.setTitle("Mot de passe incorrect");
            alert.setHeaderText("Veuillez saisir le mot de passe correctement");
            alert.setContentText("Il faut saisir au minimum "
                    + "une chiffre, un caratere et une lettre avec un totale de 8 au minimum svp ");
            alert.showAndWait();                        
	}
        if(!RegexValidation.isValidEmail(email.getText())){
//            System.err.println("le compte email est incorrect !! ");
//            throw new BadRequestException("le compte email est incorrect !! ");
            
            alert.setTitle("le compte email est incorrect !!");
            alert.setHeaderText("Veuillez saisir l'email correctement");
            alert.setContentText("Il faut saisir le compte email pour pouvoir s'inscrire");
            alert.showAndWait();
        }else if(!RegexValidation.checkName(nom.getText()) || !RegexValidation.checkName(prenom.getText())){
            alert.setTitle("le nom ou prénom est incorrect !!");
            alert.setHeaderText("Veuillez saisir le nom ou le prénom correctement");
            alert.setContentText("Il faut saisir le nom ou le prénom sans des chiffres");
            alert.showAndWait();
        }
        else if (null == nom.getText() || null == prenom.getText() || null == agee.getSelectionModel().getSelectedItem() || null == email.getText() ){
             alert.setTitle("champ vide !!");
            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Il faut remplir tous les champs pour pouvoir s'inscrire");
            alert.showAndWait();
        }else {
            user.setAge(agee.getSelectionModel().getSelectedIndex()+10);
            userService.ajouter(user);
            JavaMailUtils.sendMail(user.getEmail());
            
//            userService.rechercherParEmail(user);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/trenna/fxml/ActiveAccountUserFXML.fxml"));
            Parent root = loader.load();
            ActiveAccountUserFXMLController ac =loader.getController();
            nom.getScene().setRoot(root);
        }        
    }

    @FXML
    private void pageLogin(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trenna/fxml/LoginFXML.fxml"));
        Parent root = loader.load();
        LoginFXMLController ac =loader.getController();
        email.getScene().setRoot(root);
    }
    
}