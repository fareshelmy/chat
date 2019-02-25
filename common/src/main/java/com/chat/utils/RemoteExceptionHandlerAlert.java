/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author FARES-LAP
 */
public class RemoteExceptionHandlerAlert extends Alert {

    public RemoteExceptionHandlerAlert(AlertType alertType) {
        super(alertType);
        this.setTitle("Server down!");
        this.setContentText("Server is down! please try siging in later.");
        Optional<ButtonType> selected = this.showAndWait();
        if (!selected.isPresent() || selected.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

}
