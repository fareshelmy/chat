/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author FARES-LAP
 */
public class MyFactory {

    private static SessionFactory factory;

    public static void closeSession(Session session) {
    }

    private MyFactory() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    public static Session getSession() {
        if (factory == null) {
            new MyFactory();
        }
        return factory.openSession();
    }

}
