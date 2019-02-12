/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.interfaces;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.control.interfaces.*;

/**
 *
 * @author yasmin
 */
public interface ServerClientCommunicatDAO extends Remote {

    public void register(ClientServerCommunicatDAO clientService) throws RemoteException;

    public void unregister(ClientServerCommunicatDAO clientService) throws RemoteException;

    public void sendMessageToServer(String message) throws RemoteException;
}
