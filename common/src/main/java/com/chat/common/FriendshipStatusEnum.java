/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;

/**
 *
 * @author FARES-LAP
 */
public enum FriendshipStatusEnum {
    ACCEPTED("accepted"),
    PENDING("pending");

    String friendshipStatus;

    private FriendshipStatusEnum(String friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

    public String getFriendshipStatus() {
        return friendshipStatus;
    }

    public void setFriendshipStatus(String friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

}
