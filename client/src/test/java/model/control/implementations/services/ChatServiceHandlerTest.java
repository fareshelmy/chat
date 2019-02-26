package model.control.implementations.services;

import com.chat.common.ClientInterface;
import com.chat.common.User;
import com.chat.common.entities.Message;
import java.util.UUID;
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
public class ChatServiceHandlerTest {

    public ChatServiceHandlerTest() {
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
     * Test of registerClient method, of class ChatServiceHandler.
     */
    @Test
    public void testRegisterClient() {
        System.out.println("registerClient");
        String ID = "";
        ClientInterface client = null;
        ChatServiceHandler instance = new ChatServiceHandler();
        instance.registerClient(ID, client);

    }

    /**
     * Test of unregisterClient method, of class ChatServiceHandler.
     */
    @Test
    public void testUnregisterClient() {
        System.out.println("unregisterClient");
        String ID = "";
        ClientInterface client = null;
        ChatServiceHandler instance = new ChatServiceHandler();
        instance.unregisterClient(ID, client);

    }

    /**
     * Test of sendMessageToServer method, of class ChatServiceHandler.
     */
    @Test
    public void testSendMessageToServer() {
        System.out.println("sendMessageToServer");
        Message message = null;
        ChatServiceHandler instance = new ChatServiceHandler();
        instance.sendMessageToServer(message);

    }

    /**
     * Test of createSession method, of class ChatServiceHandler.
     */
    @Test
    public void testCreateSession() {
        System.out.println("createSession");
        ClientInterface sender = null;
        String receiverID = "";
        ChatServiceHandler instance = new ChatServiceHandler();
        UUID expResult = null;
        UUID result = instance.createSession(sender, receiverID);
        assertEquals(expResult, result);

    }

    /**
     * Test of sendMessageToSession method, of class ChatServiceHandler.
     */
    @Test
    public void testSendMessageToSession() {
        System.out.println("sendMessageToSession");
        UUID sessionID = null;
        Message message = null;
        ChatServiceHandler instance = new ChatServiceHandler();
        instance.sendMessageToSession(sessionID, message);

    }

    /**
     * Test of notifyStatusChange method, of class ChatServiceHandler.
     */
    @Test
    public void testNotifyStatusChange() {
        System.out.println("notifyStatusChange");
        User user = new User();
        ChatServiceHandler instance = new ChatServiceHandler();
        instance.notifyStatusChange(user);

    }

    /**
     * Test of sendMessageToBot method, of class ChatServiceHandler.
     */
    @Test
    public void testSendMessageToBot() {
        System.out.println("sendMessageToBot");
        String request = "Hello";
        ChatServiceHandler instance = new ChatServiceHandler();
        String expResult = null;
        String result = instance.sendMessageToBot(request);
        assertNotEquals(expResult, result);

    }

}
