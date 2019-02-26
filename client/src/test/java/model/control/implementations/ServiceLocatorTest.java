
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author yasmin
 */
public class ServiceLocatorTest {

    public ServiceLocatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getService method, of class ServiceLocator.
     */
    @Test
    public void testGetDataBaseService() {
        try {
            System.out.println("getService");
            String serviceName = "DatabaseService";
            Registry registry = LocateRegistry.getRegistry("localhost");
            Remote expResult = registry.lookup(serviceName);
            Remote result = ServiceLocator.getService(serviceName);
            assertEquals(expResult, result);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceLocatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServiceLocatorTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void testGetChatService() {
        try {
            System.out.println("getService");
            String serviceName = "ChatService";
            Registry registry = LocateRegistry.getRegistry("localhost");
            Remote expResult = registry.lookup(serviceName);
            Remote result = ServiceLocator.getService(serviceName);
            assertEquals(expResult, result);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceLocatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ServiceLocatorTest.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
