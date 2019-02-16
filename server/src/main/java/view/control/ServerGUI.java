package view.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.control.eventhandlers.ServerGUIController;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M
 */
public class ServerGUI extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        ServerGUIController serverGUIController = new ServerGUIController();
        loader.setController(serverGUIController);
        Parent serverGUI = loader.load(getClass().getResource("../view/ServerGUI.fxml").openStream());
        Scene  serverScene = new Scene(serverGUI);
        stage.setScene(serverScene);
        stage.show();
        
    }
    
}
