/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations.services;

import com.chat.common.ClientInterface;
import com.chat.common.User;
import com.chat.common.entities.Message;
import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import controller.implementations.Controller;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.UUID;
import javafx.util.Duration;

/**
 *
 * @author rokaya
 */
public class ClientServiceImp extends UnicastRemoteObject implements ClientInterface {

    Controller controller;

    public ClientServiceImp(Controller controller) throws RemoteException {
        this.controller = controller;
    }

    @Override
    public void receive(Message message) throws RemoteException {
        //controller.sendMessageToGUI(message);
        System.out.println(message.getMessage());

    }

    @Override
    public void receiveListOfOnlineFrinds(List<ClientInterface> onlineFrinds) throws RemoteException {
//        controller.sendListToGUI(onlineFrinds);
    }

    @Override
    public void reciveMessageFromSession(UUID id, Message message) throws RemoteException {
        controller.displayMessageOnSession(id, message);

    }

    @Override
    public void openSessionWindow(UUID id) throws RemoteException {
        controller.openSessionResponse(id);
    }

    @Override
    public void receiveStatusChange(User user) throws RemoteException {
        controller.receiveStatusChange(user);
    }

}
