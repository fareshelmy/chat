package view.control;

import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.UserDAO;
import view.view.MainView;

public class MainApp extends Application {

    Statement statement;
    private final UserDAO userDAO;

    public MainApp() {
        statement = DatabaseConnector.getStatement();
        userDAO = new UserDAO(statement);
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
