package view.control;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import java.io.File;
import java.io.FileInputStream;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.control.MainGUIFXMLController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.ServerService;
import model.control.implementations.UserDAOImpl;

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

    private static UserDAOImpl userDaoImpl;

    /**
     * @param args the command line arguments
     */
    public ServerGUI() {

    }

    public static void main(String[] args) {
       
        new ServerService();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent serverGUI = loader.load(getClass().getResource("/fxml/MainGUIFXML.fxml").openStream());
        MainGUIFXMLController controller = loader.getController();
        Scene scene = new Scene(serverGUI);
        scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
    }

}
