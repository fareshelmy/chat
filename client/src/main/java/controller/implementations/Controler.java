package controller.implementations;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.control.implementations.ClientServerCommunicatImp;
import model.control.implementations.ServerClientCommunicatImp;
import model.control.interfaces.*;
import view.view.*;

/**
 *
 * @author yasmin
 */
public class Controler extends Application {

    public ChatWindowInterface chatWindow;
    public ClientServerCommunicatDAO clientServerCommunicat;
    public ServerClientCommunicatDAO serverClientCommunicat;

    @Override
    public void start(Stage primaryStage) {
        try {
            serverClientCommunicat = new ServerClientCommunicatImp();
            clientServerCommunicat = new ClientServerCommunicatImp(this);
            serverClientCommunicat.register(clientServerCommunicat);
            chatWindow = new ChatWindow(this);

            Scene scene = new Scene((Parent) chatWindow, 600, 400);

            primaryStage.setTitle("Chat Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }

    }

    // @Override
    // public void stop() 
    // {
    //    serverService.unregister(clientService);
    //}
    public static void main(String[] args) {
        launch(args);
    }

    public void sendMessageToServer(String message) {
        try {
            serverClientCommunicat.sendMessageToServer(message);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void displayMessage(String receivedMessage) {
        chatWindow.display(receivedMessage);
    }

}
