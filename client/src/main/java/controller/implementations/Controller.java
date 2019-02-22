/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.implementations;

import com.chat.common.ClientInterface;
import com.chat.common.User;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import model.control.implementations.services.ChatServiceHandler;
import model.control.implementations.services.ClientServiceImp;
import model.control.implementations.services.UserDAOHandler;
import view.control.HomeViewController;

/**
 *
 * @author rokaya
 */
public class Controller {

    private ChatServiceHandler serverService;
    private ClientInterface client;
    private HomeViewController homeViewController;
    private UserDAOHandler userDAOHandler;
    private User user;

    public Controller() {
        serverService = new ChatServiceHandler();
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

    public void sendMessageToGUI(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sendListToGUI(List<ClientInterface> onlineFrinds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayMessageOnSession(UUID id, Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void openSessionResponse(UUID id) {
        homeViewController.openSessionResponse(id);
    }

    public List<User> getFriendList(User user) {
        return userDAOHandler.getUserContacts(user);
    }

    public void createSession(String phone) {
        serverService.createSession(client, phone);
    }

    public void setHomeViewController(HomeViewController homeViewController) {
        this.homeViewController=homeViewController;
    }

}
