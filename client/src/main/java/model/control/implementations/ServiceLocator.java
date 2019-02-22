package model.control.implementations;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rokaya
 */
public class ServiceLocator {

    private static Map<String, Remote> services = new HashMap<>();
    
    public static Remote getService(String serviceName) {
        Remote service = (services.containsKey(serviceName)) ? services.get(serviceName) : null;
        if (service == null) {
            try {
                Registry registry = LocateRegistry.getRegistry("localhost");
                service = registry.lookup(serviceName);
                services.put(serviceName, service);
            } catch (AccessException | NotBoundException ex) {
                Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        }
        return service;
    }
}
