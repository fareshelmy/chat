/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations.services;

import com.chat.common.User;
import com.chat.common.UserDAO;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        }
    }

    public User validatePhone(String phone) {
        try {
            return userDao.validate(phone);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<User> getUserContacts(User user) {
        List<User> contacts = null;
        try {
            contacts = userDao.retrieveContacts(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contacts;
    }

    public void update(User user) {
        try {
            userDao.update(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean addContact(User adder, User added) {
        System.out.println("model.control.implementations.services.UserDAOHandler.addContact()");
        return false;
    }

    public Integer getOfflineUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getOnlineUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeContact(User remover, User removed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<User> retrieveContacts(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
