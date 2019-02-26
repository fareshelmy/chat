package model.control.implementations.services;

import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.User;
import java.util.List;
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
public class UserDAOHandlerTest {

    User user = null;

    public UserDAOHandlerTest() {
        user = new User("01008888888", "test", "test", "000", "test@hotmail.com", new byte[1000], GenderEnum.FEMALE, "Egypt", "2018-02-20", "hello", StatusEnum.BUSY, RegisteredByEnum.USER);

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
     * Test of persistUser method, of class UserDAOHandler.
     */
    @Test
    public void testPersistUser() {
        System.out.println("persistUser");
        UserDAOHandler instance = new UserDAOHandler();
        instance.persistUser(user);

    }

    /**
     * Test of validatePhone method, of class UserDAOHandler.
     */
    @Test
    public void testValidatePhone() {
        System.out.println("validatePhone");
        String phone = "";
        UserDAOHandler instance = new UserDAOHandler();
        User expResult = null;
        User result = instance.validatePhone(phone);
        assertEquals(expResult, result);

    }

    /**
     * Test of addContact method, of class UserDAOHandler.
     */
//    @Test
//    public void testAddContact() {
//        System.out.println("addContact");
//        User adder = null;
//        User added = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        boolean expResult = false;
//        boolean result = instance.addContact(adder, added);
//        assertEquals(expResult, result);
//
//    }

    /**
     * Test of getOfflineUsers method, of class UserDAOHandler.
     */
    @Test
    public void testGetOfflineUsers() {
        System.out.println("getOfflineUsers");
        UserDAOHandler instance = new UserDAOHandler();
        Integer expResult = null;
        //Integer result = instance.getOfflineUsers();
        //  assertNotEquals(expResult, result);

    }

    /**
     * Test of getOnlineUsers method, of class UserDAOHandler.
     */
    @Test
    public void testGetOnlineUsers() {
        System.out.println("getOnlineUsers");
        UserDAOHandler instance = new UserDAOHandler();
        Integer expResult = null;
        //Integer result = instance.getOnlineUsers();
        //assertEquals(expResult, result);

    }

    /**
     * Test of removeContact method, of class UserDAOHandler.
     */
    @Test
    public void testRemoveContact() {
        System.out.println("removeContact");
        User removed = new User();
        User remover = new User();
        UserDAOHandler instance = new UserDAOHandler();
        //instance.removeContact(remover, removed);

    }
}
