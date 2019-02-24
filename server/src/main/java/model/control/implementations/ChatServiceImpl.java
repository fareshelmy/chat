package model.control.implementations;


import com.chat.common.ChatService;
import com.chat.common.ChatSession;
import com.chat.common.ClientInterface;
import com.chat.common.entities.Message;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {

    Map<String, ClientInterface> allClients;
    Map<UUID, ChatSession> sessionList;

    public ChatServiceImpl() throws RemoteException {
        allClients = new HashMap<>();
        sessionList = new HashMap<>();
    }
    

    @Override
    public void broadcast(Message message) throws RemoteException {
        for (ClientInterface client : allClients.values()) {
            client.receive(message);
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
    public void unregister(String ID,ClientInterface client) throws RemoteException {
        allClients.remove(ID,client);
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
        sessionList.put(sessionID, new ChatSession(sessionID,set));
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

}
