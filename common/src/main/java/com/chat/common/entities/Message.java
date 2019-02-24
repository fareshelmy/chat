/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common.entities;

import com.chat.common.User;
import java.io.Serializable;

/**
 *
 * @author rokaya
 */
public class Message implements Serializable {

    User user;
    String message;

    public Message(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public Message(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
