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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
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
            transferToHTML("chatsession.xml", "index.html", "xslt.xsl");
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

    public static void transferToHTML(String inputFileName, String outputFilename, String xslFilename) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Templates template = factory.newTemplates(new StreamSource(new FileInputStream(xslFilename)));
            Transformer transformer = template.newTransformer();
            Source source = new StreamSource(new FileInputStream(inputFileName));
            Result result = new StreamResult(new FileOutputStream(outputFilename));
            transformer.transform(source, result);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (TransformerConfigurationException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }
}
