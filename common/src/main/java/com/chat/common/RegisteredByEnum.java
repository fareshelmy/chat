/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;

import java.io.Serializable;

/**
 *
 * @author FARES-LAP
 */
public enum RegisteredByEnum implements Serializable {
    USER("user"),
    ADMIN("admin");

    String registeredBy;

    private RegisteredByEnum(String registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getRegisteredBy(RegisteredByEnum registeredByEnum) {
        return registeredByEnum.registeredBy;
    }

    public void setRegisteredBy(String registeredBy) {
        this.registeredBy = registeredBy;
    }

}
