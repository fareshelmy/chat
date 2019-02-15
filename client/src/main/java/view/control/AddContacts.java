package view.control;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author yasmin
 */
public class AddContacts extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        AddContactsController addContactsController = new AddContactsController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(addContactsController);
        Parent root = loader.load(getClass().getResource("src/main/resources/fxml/AddContactsFXML.fxml").openStream());

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("src/main/resources/styles/AddContactsCSS.css").toString());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
