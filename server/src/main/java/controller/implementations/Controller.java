/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.implementations;

import com.chat.common.ChatService;
import com.chat.common.User;
import com.chat.common.UserDAO;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.control.implementations.ChatServiceImpl;
import model.control.implementations.DatabaseConnector;
import model.control.implementations.ServerService;
import model.control.implementations.UserDAOImpl;

/**
 *
 * @author FARES-LAP
 */
public class Controller {

    private UserDAO userDaoImpl;
    private ChatService chatServiceImpl;
    private ServerService serverService;

    public Controller() {
        this.userDaoImpl = userDaoImpl;
        this.chatServiceImpl = chatServiceImpl;
    }

    public int getOnlineUsersCount() {
        int onlineUsersCount = 0;
        if (userDaoImpl != null) {
            try {
                onlineUsersCount = userDaoImpl.getOnlineUsers();
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return onlineUsersCount;
    }

    public int getOfflineUsersCount() {
        int offlineUsersCount = 0;
        if (userDaoImpl != null) {
            try {
                offlineUsersCount = userDaoImpl.getOfflineUsers();
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return offlineUsersCount;
    }

    public Map<String, Integer> getGenderStatistics() {
        Map<String, Integer> genderStatistics = null;
        if (userDaoImpl != null) {
            try {
                genderStatistics = userDaoImpl.getGenderStatistics();
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return genderStatistics;
    }

    public Map<String, Integer> getCountryStatistics() {
        Map<String, Integer> countryStatistics = null;
        if (userDaoImpl != null) {
            try {
                countryStatistics = userDaoImpl.getCountryStatistics();
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return countryStatistics;
    }

    public void persistUser(User user) {
        if (userDaoImpl != null) {
            try {
                userDaoImpl.persist(user);
            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Object validateUser(String phoneNumber) {
        User user = null;
        if (userDaoImpl != null) {
            try {
                user = userDaoImpl.validate(phoneNumber);

            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    public void broadcast(String message) {
        if (userDaoImpl != null) {
            try {
                chatServiceImpl.broadcast(new Message(message));

            } catch (RemoteException ex) {
                Logger.getLogger(Controller.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startService() {
        try {
            userDaoImpl = new DatabaseConnector().getUserDaoImpl();
            chatServiceImpl = new ChatServiceImpl(userDaoImpl);
            serverService = new ServerService(userDaoImpl, chatServiceImpl);
            User user = new User();
            user.setPhone("11");
            User user1 = new User();
            user1.setPhone("111");
            userDaoImpl.addContact(user1, user);
        } catch (RemoteException ex) {
            Logger.getLogger(Controller.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopService() {
        serverService.stopService();
    }

}
