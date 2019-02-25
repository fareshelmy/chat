package com.chat.common;

import com.chat.common.entities.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rokaya
 */
public interface ClientInterface extends Remote {

    public void receive(Message message) throws RemoteException;

    public void receiveListOfOnlineFrinds(List<ClientInterface> onlineFrinds) throws RemoteException;

    public void reciveMessageFromSession(UUID id, Message message) throws RemoteException;

    public void openSessionWindow(UUID id) throws RemoteException;

    public void receiveStatusChange(User user) throws RemoteException;
}
