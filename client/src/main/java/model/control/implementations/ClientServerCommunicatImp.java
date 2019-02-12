
package model.control.implementations;
import java.rmi.RemoteException;
import controller.implementations.Controler;
import model.control.interfaces.ClientServerCommunicatDAO;

public class ClientServerCommunicatImp implements ClientServerCommunicatDAO{

     private Controler controler;

    public ClientServerCommunicatImp(Controler controller) throws RemoteException {
        super();
        this.controler = controller;
    }

    @Override
    public void receive(String receivedMessage) throws RemoteException {
        controler.displayMessage(receivedMessage);

    }
    
}
