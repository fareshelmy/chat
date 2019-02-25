package model.control.implementations;

import com.chat.common.ChatService;
import com.chat.common.ChatSession;
import com.chat.common.ClientInterface;
import com.chat.common.User;
import com.chat.common.UserDAO;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    Map<String, ClientInterface> allClients;
    Map<UUID, ChatSession> sessionList;
    private final UserDAO userDaoImpl;

    public ChatServiceImpl(UserDAO userDaoImpl) throws RemoteException {
        allClients = new HashMap<>();
        sessionList = new HashMap<>();
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public void broadcast(Message message) throws RemoteException {
        for (ClientInterface client : allClients.values()) {
            if (client != null) {
                client.receive(message);
            }
        }
    }

    @Override
    public void register(String ID, ClientInterface client) throws RemoteException {
        allClients.put(ID, client);
        //////////////////////////////Temprory return all online clients //////////////////////////
//        client.receiveListOfOnlineFrinds(allClients);
        System.out.println(allClients.size());
    }

    @Override
    public void unregister(String ID, ClientInterface client) throws RemoteException {
        allClients.remove(ID, client);
        System.out.println(allClients.size());
    }

    @Override
    public UUID openSession(ClientInterface sender, String ID) throws RemoteException {
        UUID sessionID = UUID.randomUUID();
//        System.out.println(sessionID.toString());
        Set<ClientInterface> set = new HashSet<>();
        set.add(sender);
        set.add(allClients.get(ID));
        allClients.get(ID).openSessionWindow(sessionID);
        sessionList.put(sessionID, new ChatSession(sessionID, set));
        System.out.println("sessionCreated");
        return sessionID;
    }

    @Override
    public void sendMessageToSession(UUID sessionID, Message message) throws RemoteException {
        ChatSession session = sessionList.get(sessionID);
        System.out.println(session);
        session.sendMessageToSessionMembers(message);
    }

//    int id=1;
//    @Override 
//    public void getUserDate(User user) throws RemoteException {
//        user.setID(id++);
//        user.setName("H"+id);
//        user.setOnlineFrinds((List)allClients.values());
//    }
//    @Override
//    public Map<String, ClientInterface> getAllusers() throws RemoteException {
//        return allClients;
//    }
    @Override
    public void notifyStatusChange(User user) throws RemoteException {
        List<User> contactList = userDaoImpl.retrieveContacts(user);
        contactList.forEach((contact) -> {
            try {
                ClientInterface clientInterface = allClients.get(contact.getPhone());
                if (clientInterface != null) {
                    clientInterface.receiveStatusChange(user);
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ChatServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

}
