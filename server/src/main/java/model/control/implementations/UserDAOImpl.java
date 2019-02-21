package model.control.implementations;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.chat.common.User;
import com.chat.common.FriendshipStatusEnum;
import com.chat.common.GenderEnum;
import com.chat.common.RegisteredByEnum;
import com.chat.common.StatusEnum;
import com.chat.common.UserDAO;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import javax.sql.rowset.CachedRowSet;

public class UserDAOImpl extends UnicastRemoteObject implements UserDAO {

    Statement statement;

    public UserDAOImpl(Connection connection) throws RemoteException {
        try {
            this.statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.CLOSE_CURSORS_AT_COMMIT);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void persist(User user) throws RemoteException {

        try {
            String sql = "INSERT INTO USERS VALUES ('" + user.getPhone()
                    + "', '" + user.getFirstName()
                    + "', '" + user.getLastName()
                    + "', '" + user.getPassword()
                    + "', '" + user.getEmail()
                    + "', '" + user.getPic()
                    + "', '" + user.getGenderEnum()
                    + "', '" + user.getCountry()
                    + "', '" + user.getDateOfBirth()
                    + "', '" + user.getBio()
                    + "', '" + user.getStatusEnum()
                    + "', '" + user.getRegisteredByEnum() + "')";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User user) throws RemoteException {
        try {
            String sql = "UPDATE USERS SET FIRST_NAME = '" + user.getFirstName()
                    + "', LAST_NAME = '" + user.getLastName()
                    + "', PASSWORD = '" + user.getPassword()
                    + "', EMAIL = '" + user.getEmail()
                    + "', PIC = '" + user.getPic()
                    + "', GENDER = '" + user.getGenderEnum()
                    + "', COUNTRY = '" + user.getCountry()
                    + "', DATE_OF_BIRTH = '" + user.getDateOfBirth()
                    + "', BIO = '" + user.getBio()
                    + "', STATUS = '" + user.getStatusEnum()
                    + "', REGISTERED_BY = '" + user.getRegisteredByEnum()
                    + "' WHERE PHONE = '" + user.getPhone() + "'";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public User validate(String phone) throws RemoteException {
        String sql = "SELECT * FROM USERS WHERE PHONE = '" + phone + "'";
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                user = new User(resultSet.getString("PHONE"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("EMAIL"),
                        resultSet.getBytes("PIC"),
                        GenderEnum.valueOf(resultSet.getString("GENDER").toUpperCase()),
                        resultSet.getString("COUNTRY"),
                        resultSet.getString("DATE_OF_BIRTH"),
                        resultSet.getString("BIO"),
                        StatusEnum.valueOf(resultSet.getString("STATUS").toUpperCase()),
                        RegisteredByEnum.valueOf(resultSet.getString("REGISTERED_BY").toUpperCase()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    @Override
    public boolean addContact(User adder, User added) throws RemoteException {
        boolean userExists = true;
        //checks if the added user exists in the database
        String checkUserSql = "SELECT PHONE FROM USERS WHERE PHONE = '" + added.getPhone() + "'";
        try {
            ResultSet resultSet = statement.executeQuery(checkUserSql);
            if (resultSet.next()) {
                //checks if the added user has sent a friend request before
                String checkSql = "SELECT ACCEPTANCE_STATUS FROM USERS_HAVE_USERS WHERE PHONE_A = '" + added.getPhone()
                        + "' AND PHONE_B = '" + adder.getPhone() + "'";
                ResultSet checkResultSet = statement.executeQuery(checkSql);
                //checks if that frienship status is pending
                if (checkResultSet.next() && checkResultSet.getString("ACCEPTANCE_STATUS").equalsIgnoreCase(FriendshipStatusEnum.PENDING.getFriendshipStatus())) {
                    //changes this status to accepted and adds a new row for adder
                    String updateSql = "UPDATE USERS_HAVE_USERS SET ACCEPTANCE_STATUS = '"
                            + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "' WHERE PHONE_A = '" + added.getPhone()
                            + "' AND PHONE_B = '" + adder.getPhone() + "'";
                    statement.executeUpdate(updateSql);

                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "')";

                    statement.executeUpdate(insertSql);
                } else {
                    //adds a new row for this friend request
                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.PENDING.getFriendshipStatus()
                            + "')";
                    statement.executeUpdate(insertSql);
                }
            } else {
                userExists = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userExists;
    }

    @Override
    public List<User> retrieveContacts(User user) throws RemoteException {
        List<User> friendList = new ArrayList<>();
        String sql = "SELECT * FROM USERS WHERE PHONE IN (SELECT PHONE_B FROM USERS_HAVE_USERS WHERE PHONE_A = '"
                + user.getPhone() + "')";
        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                friendList.add(new User(resultSet.getString("PHONE"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getString("PASSWORD"),
                        resultSet.getString("EMAIL"),
                        resultSet.getBytes("PIC"),
                        GenderEnum.valueOf(resultSet.getString("GENDER").toUpperCase()),
                        resultSet.getString("COUNTRY"),
                        resultSet.getString("DATE_OF_BIRTH"),
                        resultSet.getString("BIO"),
                        StatusEnum.valueOf(resultSet.getString("STATUS").toUpperCase()),
                        RegisteredByEnum.valueOf(resultSet.getString("REGISTERED_BY").toUpperCase())));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return friendList;
    }

    @Override
    public void delete(User user) throws RemoteException {
        String sql = "DELETE FROM USERS WHERE PHONE = '" + user.getPhone() + "'";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeContact(User remover, User removed) throws RemoteException {
        String sql = "DELETE FROM USERS_HAVE_USERS WHERE (PHONE_A = '" + remover.getPhone()
                + "' AND PHONE_B = '" + removed.getPhone()
                + "') OR (PHONE_A = '" + removed.getPhone()
                + "' AND PHONE_B = '" + remover.getPhone()
                + "')";
        try {
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer getOnlineUsers() throws RemoteException {
        Integer result = null;
        try {
            String sql = "select Count(*) from users where status='ONLINE'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            } else {
                System.err.println("Error happend!System failed to count online users.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer getOfflineUsers() throws RemoteException {
        Integer result = null;
        try {
            String sql = "select Count(*) from users where status='OFFLINE'";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            } else {
                System.err.println("Error happend!System failed to count offline users.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<String, Integer> getGenderStatistics() throws RemoteException {
        Map<String, Integer> genderStatistics = new HashMap<>();
        try {
            String sql = "select Count(phone),gender from users group by gender";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                genderStatistics.put(resultSet.getString(2), resultSet.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return genderStatistics;
    }

    @Override
    public Map<String, Integer> getCountryStatistics() throws RemoteException {

        Map<String, Integer> countryStatistics = new HashMap<>();
        try {
            String sql = "select Count(phone),country from users group by country";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                countryStatistics.put(resultSet.getString(2), resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countryStatistics;
    }
}
