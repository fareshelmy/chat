/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author M
 */
public interface UserDAO extends Remote {

    boolean addContact(User adder, User added) throws RemoteException;

    void delete(User user) throws RemoteException;

    Map<String, Integer> getCountryStatistics() throws RemoteException;

    Map<String, Integer> getGenderStatistics() throws RemoteException;

    int getOfflineUsers() throws RemoteException;

    int getOnlineUsers() throws RemoteException;

    void persist(User user) throws RemoteException;

    void removeContact(User remover, User removed) throws RemoteException;

    List<User> retrieveContacts(User user) throws RemoteException;

    void update(User user) throws RemoteException;

    User validate(String phone) throws RemoteException;

}
