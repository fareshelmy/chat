/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;
import com.chat.common.entities.*;
/**
 *
 * @author rokaya
 */
public interface ChatService extends Remote {

    public void broadcast(Message message) throws RemoteException;

    public void registr(String ID,ClientInterface client) throws RemoteException;

    public void unRegistr(String ID,ClientInterface client) throws RemoteException;

    public UUID openSession(ClientInterface sender, String ID)throws RemoteException;

    public void sendMessageToSession(UUID sessionID, Message message)throws RemoteException;
    
//    public void getUserDate(User user)throws RemoteException;
    //testfunction
//    public Map<String,ClientInterface> getAllusers()throws RemoteException;
}
