/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;

import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rokaya
 */
public class ChatSession {

    Set<ClientInterface> clientSet;
    List<Message> messageList;
    UUID sessionID;

    public ChatSession(UUID sessionID, Set<ClientInterface> clientSet) {
        this.clientSet = clientSet;
        this.sessionID = sessionID;
        messageList=new  ArrayList<>();
    }

    public Set<ClientInterface> getClientList() {
        return clientSet;
    }

    public void addClient(ClientInterface client) {
        clientSet.add(client);
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void sendMessageToSessionMembers(Message message) {
        this.messageList.add(message);
        for (Iterator<ClientInterface> it = clientSet.iterator(); it.hasNext();) {
            try {
                ClientInterface client = it.next();
                System.out.println("CommonBehavior.ChatSession.sendMessageToSessionMembers()"+client);
                if(client!=null)
                client.reciveMessageFromSession(sessionID, message);
            } catch (RemoteException ex) {
                Logger.getLogger(ChatSession.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String toString() {
        return "ChatSession{" + "messageList=" + messageList + ", sessionID=" + sessionID + '}';
    }

}
