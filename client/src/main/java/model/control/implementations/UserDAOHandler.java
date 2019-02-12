/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

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
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            userDao = (UserDAO) registry.lookup("ChatService");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
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
            System.out.println(userDao.validate(user.getPhone(), user.getPassword()));
        } catch (RemoteException ex) {
            Logger.getLogger(UserDAOHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
