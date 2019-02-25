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
