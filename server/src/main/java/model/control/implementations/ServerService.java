package model.control.implementations;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Statement;
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

    public ServerService(Statement statement) {
        Registry registry;
        try {
            registry = LocateRegistry.getRegistry();
            registry.rebind("ChatService", new UserDAOImpl(statement));
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
