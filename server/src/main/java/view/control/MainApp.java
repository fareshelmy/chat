package view.control;

import java.rmi.RemoteException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.ServerService;
import model.control.implementations.UserDAOImpl;
import view.view.MainView;

public class MainApp extends Application {

    Statement statement;
    private UserDAOImpl userDAO = null;

    public MainApp() {
        try {
            statement = DatabaseConnector.getStatement();
            userDAO = new UserDAOImpl(statement);
            new ServerService(statement);
        } catch (RemoteException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainView root = new MainView();

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        new MainApp();
        launch(args);

    }

}
