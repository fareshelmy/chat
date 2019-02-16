/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FARES-LAP
 */
public class DatabaseConnector {

    private static DatabaseConnector databaseConnector;
    private static Statement statement;

    private DatabaseConnector() {
        try {
            //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "root");
            statement = connection.createStatement();
            new UserDAOImpl(statement);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Statement getStatement() {
        if (databaseConnector == null) {
            databaseConnector = new DatabaseConnector();
        }
        return statement;
    }
}
