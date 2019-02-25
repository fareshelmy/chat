/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import accountsJAXB.AccountType;
import accountsJAXB.AccountsType;
import com.chat.common.User;
import sessionJAXB.ChatSessionType;
import sessionJAXB.MessageType;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import sessionJAXB.ObjectFactory;
import view.control.HomeViewController;

/**
 *
 * @author FARES-LAP
 */
public class SessionJAXBHandler {

    public void saveChatSession(List<MessageType> messagesList) {
        try {
            JAXBContext context = JAXBContext.newInstance("sessionJAXB");
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ObjectFactory objectFactory = new ObjectFactory();
            ChatSessionType chatSession = objectFactory.createChatSessionType();

            for (MessageType message : messagesList) {
                chatSession.getMessage().add(message);
            }
            JAXBElement<ChatSessionType> JAXBElement = objectFactory.createChatSession(chatSession);
            marshaller.marshal(JAXBElement, new File("chatsession.xml"));
        } catch (JAXBException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<MessageType> loadChatSession() {
        List<MessageType> messagesList = null;
        try {
            JAXBContext context = JAXBContext.newInstance("sessionJAXB");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            JAXBElement JAXBMyChatSessionElement = (JAXBElement) unmarshaller.unmarshal(new File("chatsession.xml"));
            ChatSessionType chatSessionType = (ChatSessionType) JAXBMyChatSessionElement.getValue();
            messagesList = chatSessionType.getMessage();
        } catch (JAXBException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messagesList;
    }
}
