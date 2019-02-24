/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

/**
 *
 * @author FARES-LAP
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidationUtil {

    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
    private static final String PHONE_REGEX = "[0-9]{1}\\d{10}";

    private static Pattern pattern;
    private static Matcher matcher;

    public FieldValidationUtil() {

    }

    public static boolean validateEmail(String email) {
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validatePhone(String phone) {

        pattern = Pattern.compile(PHONE_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phone);
        return matcher.matches();

    }

    /*
	 * The password policy is:
	 * 
	 * At least 8 chars
	 * 
	 * Contains at least one digit
	 * 
	 * Contains at least one lower alpha char and one upper alpha char
	 * 
	 * Contains at least one char within a set of special chars (@#%$^ etc.)
	 * 
	 * Does not contain space, tab, etc.
     */
    public static boolean validatePassword(String password) {
        pattern = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
