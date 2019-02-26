/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

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
public class ChatUtilityTest {

    public ChatUtilityTest() {
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
     * Test of validateEmail method, of class ChatUtility.
     */
    @Test
    public void testValidateEmailEmpty() {
        System.out.println("validateEmail");
        String email = "";
        boolean expResult = false;
        boolean result = ChatUtility.validateEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateEmailShort() {
        System.out.println("validateEmail");
        String email = "yyyy";
        boolean expResult = false;
        boolean result = ChatUtility.validateEmail(email);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidateEmailCorrect() {
        System.out.println("validateEmail");
        String email = "yasmin@hotmail.com";
        boolean expResult = true;
        boolean result = ChatUtility.validateEmail(email);
        assertEquals(expResult, result);

    }

    /**
     * Test of validatePhone method, of class ChatUtility.
     */
    @Test
    public void testValidatePhoneShort() {
        System.out.println("validatePhone");
        String phone = "0000";
        boolean expResult = false;
        boolean result = ChatUtility.validatePhone(phone);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatePhoneEmpty() {
        System.out.println("validatePhone");
        String phone = "";
        boolean expResult = false;
        boolean result = ChatUtility.validatePhone(phone);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatePhoneCorrect() {
        System.out.println("validatePhone");
        String phone = "01004568392";
        boolean expResult = true;
        boolean result = ChatUtility.validatePhone(phone);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidatePhoneLong() {
        System.out.println("validatePhone");
        String phone = "88888888888888888888888888888";
        boolean expResult = false;
        boolean result = ChatUtility.validatePhone(phone);
        assertEquals(expResult, result);

    }

    /**
     * Test of validationPassword method, of class ChatUtility.
     */
    @Test
    public void testValidationPasswordEmpty() {
        System.out.println("validationPassword");
        String password = "";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidationPasswordShort() {
        System.out.println("validationPassword");
        String password = "45";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidationPasswordNoCapital() {
        System.out.println("validationPassword");
        String password = "aaaaaaaaaaa45";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidationPasswordNoSmall() {
        System.out.println("validationPassword");
        String password = "45JKLIIIIIIIIIIIIIIIIIIII";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidationPasswordCorrect() {
        System.out.println("validationPassword");
        String password = "@K98754442f";
        boolean expResult = true;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    @Test
    public void testValidationPasswordNoSpatial() {
        System.out.println("validationPassword");
        String password = "K98754442f";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "";
        boolean expResult = false;
        boolean result = ChatUtility.validateEmail(email);
        assertEquals(expResult, result);

    }

    /**
     * Test of validatePhone method, of class ChatUtility.
     */
    @Test
    public void testValidatePhone() {
        System.out.println("validatePhone");
        String phone = "";
        boolean expResult = false;
        boolean result = ChatUtility.validatePhone(phone);
        assertEquals(expResult, result);

    }

    /**
     * Test of validationPassword method, of class ChatUtility.
     */
    @Test
    public void testValidationPassword() {
        System.out.println("validationPassword");
        String password = "";
        boolean expResult = false;
        boolean result = ChatUtility.validationPassword(password);
        assertEquals(expResult, result);

    }

}
