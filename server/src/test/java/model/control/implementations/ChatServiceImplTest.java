///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package model.control.implementations;
//
//import com.chat.common.ClientInterface;
//import com.chat.common.User;
//import com.chat.common.entities.Message;
//import java.util.UUID;
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
//public class ChatServiceImplTest {
//    
//    public ChatServiceImplTest() {
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
//     * Test of broadcast method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testBroadcast() throws Exception {
//        System.out.println("broadcast");
//        Message message = null;
//        ChatServiceImpl instance = null;
//        instance.broadcast(message);
//    }
//
//    /**
//     * Test of register method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testRegister() throws Exception {
//        System.out.println("register");
//        String ID = "";
//        ClientInterface client = null;
//        ChatServiceImpl instance = null;
//        instance.register(ID, client);
//
//    }
//
//    /**
//     * Test of unregister method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testUnregister() throws Exception {
//        System.out.println("unregister");
//        String ID = "";
//        ClientInterface client = null;
//        ChatServiceImpl instance = null;
//        instance.unregister(ID, client);
//
//    }
//
//    /**
//     * Test of openSession method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testOpenSession() throws Exception {
//        System.out.println("openSession");
//        ClientInterface sender = null;
//        String ID = "";
//        ChatServiceImpl instance = null;
//        UUID expResult = null;
//        UUID result = instance.openSession(sender, ID);
//        assertEquals(expResult, result);
//
//    }
//
//    /**
//     * Test of sendMessageToSession method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testSendMessageToSession() throws Exception {
//        System.out.println("sendMessageToSession");
//        UUID sessionID = null;
//        Message message = null;
//        ChatServiceImpl instance = null;
//        instance.sendMessageToSession(sessionID, message);
//    }
//
//    /**
//     * Test of notifyStatusChange method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testNotifyStatusChange() throws Exception {
//        System.out.println("notifyStatusChange");
//        User user = null;
//        ChatServiceImpl instance = null;
//        instance.notifyStatusChange(user);
//
//    }
//
//    /**
//     * Test of sendMessageToBot method, of class ChatServiceImpl.
//     */
//    @org.junit.Test
//    public void testSendMessageToBot() throws Exception {
//        System.out.println("sendMessageToBot");
//        String request = "";
//        ChatServiceImpl instance = null;
//        String expResult = "";
//        String result = instance.sendMessageToBot(request);
//        assertEquals(expResult, result);
//
//    }
//    
//}
