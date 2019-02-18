/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations.services;

import com.chat.common.User;
import com.chat.common.UserDAO;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yasmi
 */
public class UserDAOHandler {

    //Reference for Server Representer Stub
    UserDAO userDao;

    public UserDAOHandler() {
//        try {
//
//            userDao = (UserDAO) ServiceLoca("DatabaseService");
//        } catch (RemoteException ex) {
//            ex.printStackTrace();
//        } catch (NotBoundException ex) {
//            ex.printStackTrace();
//        }
    }

    public void persistUser(User user) {
        try {
            userDao.persist(user);
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signInUser(User user) {
        try {
            System.out.println(userDao.validate(user.getPhone()));
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

}
