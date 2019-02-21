/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.control.implementations;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

/**
 *
 * @author FARES-LAP
 */
public class DatabaseConnector {

    public UserDAOImpl getUserDaoImpl() {
        UserDAOImpl userDAOImpl = null;
        try {
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            mysqlDataSource.setURL("jdbc:mysql://localhost:3306/chat");
            mysqlDataSource.setUser("root");
            mysqlDataSource.setPassword("root");
            Connection connection = mysqlDataSource.getConnection();
            
            userDAOImpl = new UserDAOImpl(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(DatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userDAOImpl;
    }
}
