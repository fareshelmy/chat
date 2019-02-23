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
            System.out.println("register; "+ID);
            server.registr(ID, client);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void unregisterClient(String ID, ClientInterface client) {
        try {
            server.unRegistr(ID, client);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessageToServer(Message message) {
        try {
            server.broadcast(message);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public UUID createSession(ClientInterface sender, String receiverID) {
        try {
            return server.openSession(sender, receiverID);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void sendMessageToSession(UUID sessionID, Message message) {
        try {
            server.sendMessageToSession(sessionID, message);
        } catch (RemoteException ex) {
            Logger.getLogger(ChatServiceHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
