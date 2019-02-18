package view.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.ServerService;
import model.control.implementations.UserDAOImpl;
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
    public ServerGUI() {
        UserDAOImpl userDaoImpl = new DatabaseConnector().getUserDaoImpl();
        new ServerService(userDaoImpl);
    }

    public static void main(String[] args) {
        // TODO code application logic here
        new ServerGUI();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        Parent serverGUI = loader.load(getClass().getResource("/fxml/ServerGUI.fxml").openStream());
        ServerGUIController controller = loader.getController();
        Scene serverScene = new Scene(serverGUI);
        stage.setScene(serverScene);
        stage.show();

    }

}
