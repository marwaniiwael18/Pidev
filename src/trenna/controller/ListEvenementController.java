/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trenna.controller;

import trenna.common.RegexValidation;
import trenna.entities.Commentaire;
import trenna.entities.evenement;
import javafx.geometry.Insets;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import trenna.services.CommentaireService;
import trenna.services.evenementservice;
import trenna.entities.EnvoyerEmail;
import javafx.scene.control.Alert;
import trenna.fxml.MyListener;

/**
 * FXML Controller class
 *
 * @author medom
 */
public class ListEvenementController implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Button Ajouterbtn;
    evenementservice ps= new evenementservice();
   List<evenement> evenements =ps.readAll();
       private MyListener myListener;
    @FXML
    private VBox Pubdetail;
    @FXML
    private TextField id_even;
    @FXML
    private TextField comment;
    @FXML
    private TextField note;
    @FXML
    private Button Ajouterbtncomment;
    @FXML
    private TextField id_guest;
    @FXML
    private ListView<Commentaire> L_afficher;
    @FXML
    private TextField Moynote;
    @FXML
    private TextField mail;
    @FXML
    private Button buttonmail;
       static Commentaire selectedItem5;
       
          
       public  void setChosenEvent(evenement e) {
           CommentaireService cs= new CommentaireService();
           
         nom.setText(e.getNom());
         date.setText(String.valueOf(e.getDateEven()));
         prix.setText(String.valueOf( e.getPrix()));
         //recompense.setText(String.valueOf( e.getRecompense()));
         id_even.setText(String.valueOf( e.getIdEven()));
         Moynote.setText(String.valueOf(cs.GEtMoyRating(e.getIdEven())));
         
     
     }
    @FXML
    private TextField nom;
    @FXML
    private TextField date;
    @FXML
    private TextField prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         grid.getChildren().clear();
         
             if (evenements.size() > 0) {
            setChosenEvent(evenements.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(evenement e) {
                    CommentaireService cs= new CommentaireService();
                    setChosenEvent(e);
                    L_afficher.getItems().addAll(cs.afficherCommentairee(e.getIdEven()));
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenements.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventCardController itemController= fxmlLoader.getController();
                itemController.setData(evenements.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(20));
            }
        } catch (IOException e) {
        }
        
    }    

    @FXML
    private void AjouterEvenement(ActionEvent event) {
      
  try {
            Parent parent = FXMLLoader.load(getClass().getResource("AjouterEvenement.fxml"));
            Scene scene =new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
           
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void rechaff(List<evenement> evenements)
    {
     int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < evenements.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("EventCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                EventCardController itemController= fxmlLoader.getController();
                itemController.setData(evenements.get(i),myListener);

                if (column == 1) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(20));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    
    }

    @FXML
    private void AjouterCommentaire(ActionEvent event) {
         evenement pub = new evenement();
        evenementservice pubService = new evenementservice();
        Commentaire c= new Commentaire();
        CommentaireService cs = new CommentaireService();
        c.setId_even(Integer.parseInt(id_even.getText()));
        c.setNom(nom.getText());       
        c.setComment(comment.getText());
        c.setDate_com(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        //c.setId(Integer.parseInt(id.getText()));
        c.setId_guest(Integer.parseInt(id_guest.getText()));
        c.setNote(Integer.parseInt(note.getText()));
        
        
 
        cs.ajouterCommentaire(c);
    }

    @FXML
    private void sendmail(ActionEvent event) throws Exception {
        EnvoyerEmail em= new EnvoyerEmail();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!RegexValidation.isValidEmail(mail.getText())){
            alert.setTitle("le compte email est incorrect !!");
            alert.setHeaderText("Veuillez saisir l'email correctement");
            alert.setContentText("Il faut saisir le compte email pour pouvoir s'inscrire");
            alert.showAndWait();
        }else
            em.sendMail(mail.getText(),"aziz");
           

        
    }

    @FXML
    private void index(MouseEvent event) {
        selectedItem5 = L_afficher.getSelectionModel().getSelectedItem();
        
        try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Commentaire.fxml"));
            Parent root = loader.load();
            CommentaireController ac =loader.getController();
            
            
            id_even.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
}
