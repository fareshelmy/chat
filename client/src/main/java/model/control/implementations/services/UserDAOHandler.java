/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations.services;

import com.chat.common.User;
import com.chat.common.UserDAO;
import com.chat.utils.RemoteExceptionHandlerAlert;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import model.control.implementations.ServiceLocator;

/**
 *
 * @author yasmin
 */
public class UserDAOHandler {

    //Reference for Server Representer Stub
    UserDAO userDao;

    public UserDAOHandler() {
        userDao = (UserDAO) ServiceLocator.getService("DatabaseService");
    }

    public void persistUser(User user) {
        try {
            userDao.persist(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public User validatePhone(String phone) {
        try {
            return userDao.validate(phone);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
        return null;
    }

    public List<User> getUserContacts(User user) {
        List<User> contacts = null;
        try {
            contacts = userDao.retrieveContacts(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
        return contacts;
    }

    public void update(User user) {
        try {
            userDao.update(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
            new RemoteExceptionHandlerAlert(Alert.AlertType.ERROR);
        }
    }

    public boolean addContact(User adder, User added) {
        System.out.println("model.control.implementations.services.UserDAOHandler.addContact()");
        return false;
    }
}
