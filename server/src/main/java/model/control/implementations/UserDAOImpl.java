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
import javax.sql.rowset.CachedRowSet;

public class UserDAOImpl extends UnicastRemoteObject implements UserDAO {

    CachedRowSet cachedRowSet;
    Connection connection;

    public UserDAOImpl(CachedRowSet cachedRowSet, Connection connection) throws RemoteException {
        this.cachedRowSet = cachedRowSet;
        this.connection = connection;
    }

    @Override
    public void persist(User user) throws RemoteException {

        String sql = "INSERT INTO USERS (PHONE, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, PIC, GENDER, COUNTRY, DATE_OF_BIRTH, BIO, STATUS, REGISTERED_BY) VALUES ('"
                + user.getPhone()
                + "', '" + user.getFirstName()
                + "', '" + user.getLastName()
                + "', '" + user.getPassword()
                + "', '" + user.getEmail()
                + "', '" + (user.getPic() == null ? "00" : user.getPic())
                + "', '" + (user.getGenderEnum() == null ? GenderEnum.MALE : user.getGenderEnum())
                + "', '" + user.getCountry()
                + "', '" + (user.getDateOfBirth() == null ? "00" : user.getDateOfBirth())
                + "', '" + (user.getBio() == null ? "Hey there! I am using MrHappy." : user.getBio())
                + "', '" + (user.getStatusEnum() == null ? StatusEnum.ONLINE : user.getStatusEnum())
                + "', '" + user.getRegisteredByEnum() + "')";
        try {
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();
            cachedRowSet.acceptChanges(connection);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User user) throws RemoteException {
        String sql = "UPDATE USERS SET FIRST_NAME = '" + user.getFirstName()
                + "', LAST_NAME = '" + user.getLastName()
                + "', PASSWORD = '" + user.getPassword()
                + "', EMAIL = '" + user.getEmail()
                + "', PIC = '" + (user.getPic() == null ? "00" : user.getPic())
                + "', GENDER = '" + user.getGenderEnum()
                + "', COUNTRY = '" + user.getCountry()
                + "', DATE_OF_BIRTH = '" + (user.getDateOfBirth() == null ? "00" : user.getDateOfBirth())
                + "', BIO = '" + user.getBio()
                + "', STATUS = '" + user.getStatusEnum()
                + "', REGISTERED_BY = '" + user.getRegisteredByEnum()
                + "' WHERE PHONE = '" + user.getPhone() + "'";
        try {
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();
            cachedRowSet.acceptChanges(connection);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//should return User

    // method validatePhone should be added
    @Override
    public User validate(String phone) throws RemoteException {
        String sql = "SELECT * FROM USERS WHERE PHONE = '" + phone + "'";
        User user = null;
        try {
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                user = new User(cachedRowSet.getString("PHONE"),
                        cachedRowSet.getString("FIRST_NAME"),
                        cachedRowSet.getString("LAST_NAME"),
                        cachedRowSet.getString("PASSWORD"),
                        cachedRowSet.getString("EMAIL"),
                        //                        cachedRowSet.getBlob("PIC"),
                        null,
                        GenderEnum.valueOf(cachedRowSet.getString("GENDER").toUpperCase()),
                        cachedRowSet.getString("COUNTRY"),
                        cachedRowSet.getString("DATE_OF_BIRTH"),
                        cachedRowSet.getString("BIO"),
                        StatusEnum.valueOf(cachedRowSet.getString("STATUS").toUpperCase()),
                        RegisteredByEnum.valueOf(cachedRowSet.getString("REGISTERED_BY").toUpperCase()));
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
            cachedRowSet.setCommand(checkUserSql);
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                //checks if the added user has sent a friend request before
                String checkSql = "SELECT FRIENDSHIP_STATUS FROM USERS_HAVE_USERS WHERE PHONE_A = '" + added.getPhone()
                        + "' AND PHONE_B = '" + adder.getPhone() + "'";
                cachedRowSet.setCommand(checkUserSql);
                cachedRowSet.execute();
                //checks if that frienship status is pending
                if (cachedRowSet.next() && cachedRowSet.getString("FRIENDSHIP_STATUS").equals(FriendshipStatusEnum.PENDING.getFriendshipStatus())) {
                    //changes this status to accepted and adds a new row for adder
                    String updateSql = "UPDATE USERS_HAVE_USERS SET FRIENDSHIP_STATUS = '"
                            + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "' WHERE PHONE_A = '" + added.getPhone()
                            + "' AND PHONE_B = '" + adder.getPhone() + "'";
                    cachedRowSet.setCommand(updateSql);
                    cachedRowSet.execute();
                    cachedRowSet.acceptChanges(connection);

                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "')";

                    cachedRowSet.setCommand(insertSql);
                    cachedRowSet.execute();
                    cachedRowSet.acceptChanges(connection);
                } else {
                    //adds a new row for this friend request
                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.PENDING.getFriendshipStatus()
                            + "')";
                    cachedRowSet.setCommand(insertSql);
                    cachedRowSet.execute();
                    cachedRowSet.acceptChanges(connection);
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
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();

            while (cachedRowSet.next()) {
                friendList.add(new User(cachedRowSet.getString("PHONE"),
                        cachedRowSet.getString("FIRST_NAME"),
                        cachedRowSet.getString("LAST_NAME"),
                        cachedRowSet.getString("PASSWORD"),
                        cachedRowSet.getString("EMAIL"),
                        cachedRowSet.getBlob("PIC"),
                        GenderEnum.valueOf(cachedRowSet.getString("GENDER").toUpperCase()),
                        cachedRowSet.getString("COUNTRY"),
                        cachedRowSet.getString("DATE_OF_BIRTH"),
                        cachedRowSet.getString("BIO"),
                        StatusEnum.valueOf(cachedRowSet.getString("STATUS").toUpperCase()),
                        RegisteredByEnum.valueOf(cachedRowSet.getString("REGISTERED_BY").toUpperCase())));
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
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();
            cachedRowSet.acceptChanges(connection);

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
            cachedRowSet.setCommand(sql);
            cachedRowSet.execute();
            cachedRowSet.acceptChanges(connection);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Integer getOnlineUsers() throws RemoteException {
        Integer result = null;
        try {
            cachedRowSet.setCommand("select Count(*) from users where status='ONLINE'");
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                result = cachedRowSet.getInt(1);
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
            cachedRowSet.setCommand("select Count(*) from users where status='OFFLINE'");
            cachedRowSet.execute();
            if (cachedRowSet.next()) {
                result = cachedRowSet.getInt(1);
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
            cachedRowSet.setCommand("select Count(phone),gender from users group by gender");
            cachedRowSet.execute();
            while (cachedRowSet.next()) {
                genderStatistics.put(cachedRowSet.getString(2), cachedRowSet.getInt(1));
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
            cachedRowSet.setCommand("select Count(phone),country from users group by country");
            cachedRowSet.execute();
            while (cachedRowSet.next()) {
                countryStatistics.put(cachedRowSet.getString(2), cachedRowSet.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return countryStatistics;
    }
}
