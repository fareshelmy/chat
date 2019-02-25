///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model.control.implementations;
//
//import com.chat.common.User;
//import java.util.List;
//import java.util.Map;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author yasmi
// */
//public class UserDAOImplTest {
//    
//    public UserDAOImplTest() {
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
//     * Test of persist method, of class UserDAOImpl.
//     */
//    @Test
//    public void testPersist() throws Exception {
//        System.out.println("persist");
//        User user = null;
//        UserDAOImpl instance = null;
//        instance.persist(user);
//
//    }
//
//    /**
//     * Test of update method, of class UserDAOImpl.
//     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        User user = null;
//        UserDAOImpl instance = null;
//        instance.update(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of validate method, of class UserDAOImpl.
//     */
//    @Test
//    public void testValidate() throws Exception {
//        System.out.println("validate");
//        String phone = "";
//        UserDAOImpl instance = null;
//        User expResult = null;
//        User result = instance.validate(phone);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of addContact method, of class UserDAOImpl.
//     */
//    @Test
//    public void testAddContact() throws Exception {
//        System.out.println("addContact");
//        User adder = null;
//        User added = null;
//        UserDAOImpl instance = null;
//        boolean expResult = false;
//        boolean result = instance.addContact(adder, added);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of retrieveContacts method, of class UserDAOImpl.
//     */
//    @Test
//    public void testRetrieveContacts() throws Exception {
//        System.out.println("retrieveContacts");
//        User user = null;
//        UserDAOImpl instance = null;
//        List<User> expResult = null;
//        List<User> result = instance.retrieveContacts(user);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of delete method, of class UserDAOImpl.
//     */
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        User user = null;
//        UserDAOImpl instance = null;
//        instance.delete(user);
//
//    }
//
//    /**
//     * Test of removeContact method, of class UserDAOImpl.
//     */
//    @Test
//    public void testRemoveContact() throws Exception {
//        System.out.println("removeContact");
//        User remover = null;
//        User removed = null;
//        UserDAOImpl instance = null;
//        instance.removeContact(remover, removed);
//    }
//
//    /**
//     * Test of getOnlineUsers method, of class UserDAOImpl.
//     */
//    @Test
//    public void testGetOnlineUsers() throws Exception {
//        System.out.println("getOnlineUsers");
//        UserDAOImpl instance = null;
//        int expResult = 0;
//        int result = instance.getOnlineUsers();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getOfflineUsers method, of class UserDAOImpl.
//     */
//    @Test
//    public void testGetOfflineUsers() throws Exception {
//        System.out.println("getOfflineUsers");
//        UserDAOImpl instance = null;
//        int expResult = 0;
//        int result = instance.getOfflineUsers();
//        assertEquals(expResult, result);
// 
//    }
//
//    /**
//     * Test of getGenderStatistics method, of class UserDAOImpl.
//     */
//    @Test
//    public void testGetGenderStatistics() throws Exception {
//        System.out.println("getGenderStatistics");
//        UserDAOImpl instance = null;
//        Map<String, Integer> expResult = null;
//        Map<String, Integer> result = instance.getGenderStatistics();
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of getCountryStatistics method, of class UserDAOImpl.
//     */
//    @Test
//    public void testGetCountryStatistics() throws Exception {
//        System.out.println("getCountryStatistics");
//        UserDAOImpl instance = null;
//        Map<String, Integer> expResult = null;
//        Map<String, Integer> result = instance.getCountryStatistics();
//        assertEquals(expResult, result);
//
//    }
//    
//}
