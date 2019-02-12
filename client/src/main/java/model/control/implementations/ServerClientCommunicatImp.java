/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.control.interfaces.ServerClientCommunicatDAO;
import model.control.interfaces.ServerInterface;
import model.control.interfaces.ClientServerCommunicatDAO;

/**
 *
 * @author yasmi
 */
public class ServerClientCommunicatImp implements ServerClientCommunicatDAO {

    ServerInterface serverRefrence;

    public ServerClientCommunicatImp() {
        try {

            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 2000);
            serverRefrence = (ServerInterface) registry.lookup("chat");
        } catch (RemoteException ex) {
            ex.printStackTrace();
        } catch (NotBoundException ex) {
            ex.printStackTrace();
        }
    }

    public void register(ClientServerCommunicatDAO clientServerCommunicat) {
        try {
            serverRefrence.register(clientServerCommunicat);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void unregister(ClientServerCommunicatDAO clientServerCommunicat) {
        try {
            serverRefrence.unregister(clientServerCommunicat);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMessageToServer(String message) {
        try {
            serverRefrence.broadCast(message);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
