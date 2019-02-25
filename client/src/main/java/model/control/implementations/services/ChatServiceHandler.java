/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations.services;

import com.chat.common.ClientInterface;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.control.implementations.ServiceLocator;
import com.chat.common.ChatService;
import com.chat.common.User;
import com.chat.utils.RemoteExceptionHandlerAlert;
import javafx.scene.control.Alert;

/**
 *
 * @author rokaya
 */
public class ChatServiceHandler {

    private ChatService server;

    public ChatServiceHandler() {
        server = (ChatService) ServiceLocator.getService("ChatService");
    }

    public void registerClient(String ID, ClientInterface client) {
        try {
            System.out.println("register; " + ID);
            server.register(ID, client);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public void unregisterClient(String ID, ClientInterface client) {
        try {
            server.unregister(ID, client);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public void sendMessageToServer(Message message) {
        try {
            server.broadcast(message);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public UUID createSession(ClientInterface sender, String receiverID) {
        try {
            return server.openSession(sender, receiverID);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
        return null;
    }

    public void sendMessageToSession(UUID sessionID, Message message) {
        try {
            server.sendMessageToSession(sessionID, message);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public void notifyStatusChange(User user) {
        try {
            server.notifyStatusChange(user);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public String sendMessageToBot(String request) {
        String response = "Mr.Happy is not avaliable right now! :(";
        try {
            response = server.sendMessageToBot(request);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;

    }

}
