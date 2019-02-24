package model.control.implementations;

import com.chat.common.ChatService;
import com.chat.common.UserDAO;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author FARES-LAP
 */
public class ServerService {

    private Registry registry;

    public ServerService(UserDAO userDaoImpl, ChatService chatServiceImpl) {
        try {
            registry = LocateRegistry.getRegistry();
            registry.rebind("DatabaseService", userDaoImpl);
            registry.rebind("ChatService", chatServiceImpl);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void stopService() {
        try {
            registry.unbind("DatabaseService");
            registry.unbind("ChatService");
        } catch (RemoteException ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
