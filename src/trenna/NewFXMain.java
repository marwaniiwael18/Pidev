package trenna;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.stage.Stage;

/**
 *
 * @author moham
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/trenna/fxml/UserFXML.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root,869,583); 
        scene.getStylesheets().add("/trenna/fxml/UserFXML.css");
//        Image image = new Image("file:..\\..\\..\\..\\..\\..\\Bureau\\istockphoto-1136317339-612x612.jpg");
//        ImageView imageView = new ImageView(image);

        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
