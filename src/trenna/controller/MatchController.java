/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package trenna.controller;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import trenna.entities.Mailing;
import trenna.services.ServiceArb;
import trenna.entities.Arbitre;
import trenna.entities.Pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import trenna.services.ServiceArb;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import trenna.services.ServiceMatch;
import org.controlsfx.control.Notifications ; 
import trenna.entities.Pdf_1;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class MatchController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField nom;
    @FXML
    private TextField specialite;
    @FXML
    private Button ajt;
    @FXML
    private Label listP;
    @FXML
    private Button afficher;
    @FXML
    private AnchorPane af;
    @FXML
    private Button supp;
    @FXML
    private Button nx;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<String> choisir;
    @FXML
    private TextField rec;
    @FXML
    private Button btnTrier;
    @FXML
    private Button rt;
    @FXML
    private Button pdfm;
   
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // fillComboSupprimer();
        choisir.getItems().addAll(
        "idArb",
        "nomArb",
        "specialite",
        "email"
        );
    }    
    
 
 

    @FXML
    private void ajouter(ActionEvent event) throws Exception {
          
        
  /*            StringBuilder errors=new StringBuilder();
        //.trim ==> tna7ili les espace khater ken fama espace twali ma aadech empty hors lezem tkoun empty donc naamlou trim
        //.append ==> ken par exemple ma ktebtech adresse w email donc ijibli ken lekhrenya donc .append yaamle aala l lkoll 
        //kima une list kol ma nektebech tzid'ha f lista w baeed yaffichili ili ma ktebtech fih
        if(nom.getText().trim().isEmpty()){
            errors.append("Please enter an name\n");
        }
        if(specialite.getText().trim().isEmpty() ){
            errors.append("Please enter an specialite\n");
       
        if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
        else{
                    ServiceArb sa = new ServiceArb() ;

            Arbitre a =new Arbitre();
            a.setNomArb(nom.getText());
            a.setSpecialite(specialite.getText());
            sa.ajouterArb(a);

        }


        
    
    
    }*/
  
     ServiceArb sa = new ServiceArb() ;   
      StringBuilder errors=new StringBuilder();
        
         if(nom.getText().trim().isEmpty()&&specialite.getText().trim().isEmpty()){
            errors.append("svp enter un nom et specialité\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
       Arbitre a = new Arbitre(nom.getText(),specialite.getText(),email.getText());
        
            
        sa.ajouterArb(a);
         Mailing.sendMail(a.getEmail(), a.getNomArb()) ;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText(" vous etes ajoute avec succes veuillez cousulter fotre email !");
            alert.show();
            nom.setText("");
            specialite.setText("");
            email.setText("");
        Notifications notificationBuilder = Notifications.create()
        .title("Alert").text("Panier ajoutÃ© avec succÃ©").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();

  
    }
    
   

    @FXML
    private void afficher(ActionEvent event) throws IOException {
                 ServiceArb sa = new ServiceArb() ;   
        
            listP.setText(sa.afficherArb().toString());
//            listP.alignmentProperty();
//            listP.getTextFill();
   
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
                         ServiceArb sa = new ServiceArb() ;   

            

      StringBuilder errors=new StringBuilder();
        
        if(id.getText().trim().isEmpty()){
            errors.append("svp enter un id\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     
                   sa.supprimerArb(Integer.parseInt(id.getText()));

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Arbitre is delete successfully!");
            alert.show();
          
    }

    @FXML
    private void next(ActionEvent event) throws IOException {
        
        

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/arbitre.fxml"));
                Parent root = loader.load();
                ArbitreController af = loader.getController();
                nom.getScene().setRoot(root);

    }

  
    @FXML
    private void rec(KeyEvent event) {
                 ServiceArb sa = new ServiceArb() ;   
    
    listP.setText(sa.RechercherArb(choisir.getSelectionModel().getSelectedItem(),rec.getText()).toString());
    }

    @FXML
    private void affichrtTri(ActionEvent event) {
    
    
    ServiceArb sa = new ServiceArb() ;   
    listP.setText(sa.TrierArb(choisir.getSelectionModel().getSelectedItem()).toString());
    }

    @FXML
    private void rt(ActionEvent event) throws IOException{
             FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/InterfaceArbMatch.fxml"));
                Parent root = loader.load();
                InterfaceArbMatchController aa = loader.getController();
                rt.getScene().setRoot(root);
    }

    @FXML
    private void pdfm(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf_1 pd=new Pdf_1();
        try{
        pd.GeneratePdfm("list of arbitre");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(ServiceMatch.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
}
    
    
       
    
    
    
    

  
    


