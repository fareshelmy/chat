package model.control.implementations;

import com.chat.common.UserDAO;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

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

    public ServerService() {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            UserDAO userDaoImpl = new DatabaseConnector().getUserDaoImpl();
            registry.rebind("DatabaseService", userDaoImpl);
            ChatServiceImp chatService = new ChatServiceImp();
            registry.rebind("ChatService", chatService);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
