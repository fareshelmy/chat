/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.interfaces;

import java.util.List;
import java.util.Map;
import model.entities.User;

/**
 *
 * @author M
 */
public interface IUserDAO {

    boolean addContact(User adder, User added);

    void delete(User user);

    Map<String, Integer> getCountryStatistics();

    Map<String, Integer> getGenderStatistics();

    Integer getOfflineUsers();

    Integer getOnlineUsers();

    void persist(User user);

    void removeContact(User remover, User removed);

    List<User> retrieveContacts(User user);

    void update(User user);

    boolean validate(String phone, String password);
    
}
