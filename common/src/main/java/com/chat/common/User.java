/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chat.common;

import java.awt.Image;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


/**
 *
 * @author FARES-LAP
 */
public class User implements Serializable {

    String phone;
    String firstName;
    String lastName;
    String password;
    String email;
    Blob pic;
    GenderEnum genderEnum;
    String country;
    String dateOfBirth;
    String bio;
    StatusEnum statusEnum;
    RegisteredByEnum registeredByEnum;

    public User(String phone, String firstName, String lastName, String password, String email, Blob pic, GenderEnum genderEnum, String country, String dateOfBirth, String bio, StatusEnum statusEnum, RegisteredByEnum registeredByEnum) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.pic = pic;
        this.genderEnum = genderEnum;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.statusEnum = statusEnum;
        this.registeredByEnum = registeredByEnum;
    }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Blob getPic() {
        return pic;
    }

    public void setPic(Blob pic) {
        this.pic = pic;
    }

    public String getGender() {
        return genderEnum.getGender(genderEnum);
    }

    public void setGenderEnum(GenderEnum genderEnum) {
        this.genderEnum = genderEnum;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStatus() {
        return statusEnum.getStatus(statusEnum);
    }

    public void setStatusEnum(StatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    public String getRegisteredBy() {
        return registeredByEnum.getRegisteredBy(registeredByEnum);
    }

    public void setRegisteredBy(RegisteredByEnum registeredByEnum) {
        this.registeredByEnum = registeredByEnum;
    }

}
