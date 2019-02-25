///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model.control.implementations.services;
//
//import com.chat.common.User;
//import java.util.List;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author yasmin
// */
//public class UserDAOHandlerTest {
//    
//    public UserDAOHandlerTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of persistUser method, of class UserDAOHandler.
//     */
//    @Test
//    public void testPersistUser() {
//        System.out.println("persistUser");
//        User user = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        instance.persistUser(user);
//
//    }
//
//    /**
//     * Test of validatePhone method, of class UserDAOHandler.
//     */
//    @Test
//    public void testValidatePhone() {
//        System.out.println("validatePhone");
//        String phone = "";
//        UserDAOHandler instance = new UserDAOHandler();
//        User expResult = null;
//        User result = instance.validatePhone(phone);
//        assertEquals(expResult, result);
//   
//    }
//
//    /**
//     * Test of getUserContacts method, of class UserDAOHandler.
//     */
//    @Test
//    public void testGetUserContacts() {
//        System.out.println("getUserContacts");
//        User user = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        List<User> expResult = null;
//        List<User> result = instance.getUserContacts(user);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of update method, of class UserDAOHandler.
//     */
//    @Test
//    public void testUpdate() {
//        System.out.println("update");
//        User user = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        instance.update(user);
//
//    }
//
//    /**
//     * Test of addContact method, of class UserDAOHandler.
//     */
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
//
//    /**
//     * Test of getOfflineUsers method, of class UserDAOHandler.
//     */
//    @Test
//    public void testGetOfflineUsers() {
//        System.out.println("getOfflineUsers");
//        UserDAOHandler instance = new UserDAOHandler();
//        Integer expResult = null;
//        Integer result = instance.getOfflineUsers();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getOnlineUsers method, of class UserDAOHandler.
//     */
//    @Test
//    public void testGetOnlineUsers() {
//        System.out.println("getOnlineUsers");
//        UserDAOHandler instance = new UserDAOHandler();
//        Integer expResult = null;
//        Integer result = instance.getOnlineUsers();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of removeContact method, of class UserDAOHandler.
//     */
//    @Test
//    public void testRemoveContact() {
//        System.out.println("removeContact");
//        User remover = null;
//        User removed = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        instance.removeContact(remover, removed);
//
//    }
//
//    /**
//     * Test of retrieveContacts method, of class UserDAOHandler.
//     */
//    @Test
//    public void testRetrieveContacts() {
//        System.out.println("retrieveContacts");
//        User user = null;
//        UserDAOHandler instance = new UserDAOHandler();
//        List<User> expResult = null;
//        List<User> result = instance.retrieveContacts(user);
//        assertEquals(expResult, result);
//
//    }
//    
//}
