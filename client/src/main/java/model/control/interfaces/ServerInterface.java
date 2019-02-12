/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import model.control.interfaces.ClientServerCommunicatDAO;

/**
 *
 * @author yasmin
 */
public interface ServerInterface extends Remote {

    void register(ClientServerCommunicatDAO clientServerCommunicat) throws RemoteException;

    void unregister(ClientServerCommunicatDAO clientServerCommunicat) throws RemoteException;

    void broadCast(String message) throws RemoteException;
}
