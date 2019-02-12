
package model.control.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author yasmin
 */
public interface ClientServerCommunicatDAO extends Remote{
    void receive(String receivedMessage) throws RemoteException;
}
