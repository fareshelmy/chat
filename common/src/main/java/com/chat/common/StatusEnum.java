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
public enum StatusEnum implements Serializable {
    ONLINE("online"),
    AVAILABLE("available"),
    BUSY("busy"),
    AWAY("away"),
    OFFLINE("offline");

    String status;

    private StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus(StatusEnum statusEnum) {
        return statusEnum.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
