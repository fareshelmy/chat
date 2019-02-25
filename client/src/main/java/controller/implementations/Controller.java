/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.implementations;

import com.chat.common.ClientInterface;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.control.implementations.services.ChatServiceHandler;
import model.control.implementations.services.ClientServiceImp;
import model.control.implementations.services.UserDAOHandler;
import view.control.HomeViewController;

/**
 *
 * @author rokaya
 */
public class Controller {

    private ChatServiceHandler chatService;
    private ClientInterface client;
    private HomeViewController homeViewController;
    private UserDAOHandler userDAOHandler;
    private User user;

    public Controller() {
        chatService = new ChatServiceHandler();
        userDAOHandler = new UserDAOHandler();

        try {
            client = new ClientServiceImp(this);
            //  controller = new HomeViewController(user);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void persistUser(User user) {
        userDAOHandler.persistUser(user);
    }

    public User validatePhone(String phone) {
        return userDAOHandler.validatePhone(phone);
    }

    public void displayMessageOnSession(UUID id, Message message) {
        homeViewController.displayMessageOnSession(id, message);
    }

    public void openSessionResponse(UUID id) {
        homeViewController.openSessionResponse(id);
    }

    public List<User> getFriendList(User user) {
        return userDAOHandler.getUserContacts(user);
    }

    public UUID createSession(User freind) {
        return chatService.createSession(client, freind.getPhone());
    }

    public void setHomeViewController(HomeViewController homeViewController) {
        this.homeViewController = homeViewController;
    }

    public void registerUser(User user) {
        chatService.registerClient(user.getPhone(), client);
    }

    public void sendMessageToSession(UUID sessionID, Message message) {
        chatService.sendMessageToSession(sessionID, message);
    }

    public void updateUser(User user) {
        userDAOHandler.update(user);

    }

    public void notifyStatusChange(User user) {
        chatService.notifyStatusChange(user);
    }

    public void receiveStatusChange(User user) {
        homeViewController.receiveStatusChange(user);
    }
}
