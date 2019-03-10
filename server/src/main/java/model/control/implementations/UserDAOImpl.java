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
import controller.implementations.Users;
import controller.implementations.UsersHaveUsers;
import controller.implementations.UsersHaveUsersId;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.text.DateFormat;
import java.util.HashSet;
import javax.sql.rowset.CachedRowSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDAOImpl extends UnicastRemoteObject implements UserDAO {

    public UserDAOImpl() throws RemoteException {
    }

    @Override
    public void persist(User user) throws RemoteException {
        Session session = MyFactory.getSession();
        Users userObject = new Users(user.getPhone(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getPic(),
                user.getGenderEnum(),
                user.getCountry(),
                user.getDateOfBirth(),
                user.getBio(),
                user.getStatusEnum(),
                user.getRegisteredByEnum(), new HashSet(0), new HashSet(0));
        session.beginTransaction();
        session.persist(userObject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) throws RemoteException {
        Session session = MyFactory.getSession();
        Users userObject = new Users(user.getPhone(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getPic(),
                user.getGenderEnum(),
                user.getCountry(),
                user.getDateOfBirth(),
                user.getBio(),
                user.getStatusEnum(),
                user.getRegisteredByEnum(), new HashSet(0), new HashSet(0));
        session.beginTransaction();
        session.saveOrUpdate(userObject);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User validate(String phone) throws RemoteException {
        Session session = MyFactory.getSession();
        Users userObject = (Users) session.get(Users.class, phone);
        User user = new User(userObject.getPhone(),
                userObject.getFirstName(),
                userObject.getLastName(),
                userObject.getPassword(),
                userObject.getEmail(),
                userObject.getPic(),
                GenderEnum.valueOf(userObject.getGender().toUpperCase()),
                userObject.getCountry(),
                userObject.getDateOfBirth(),
                userObject.getBio(),
                StatusEnum.valueOf(userObject.getStatus().toUpperCase()),
                RegisteredByEnum.valueOf(userObject.getRegisteredBy().toUpperCase()));
        MyFactory.closeSession(session);
        return user;
    }

    @Override
    public boolean addContact(User adder, User added) throws RemoteException {
        boolean userExists = true;
//        checks if the added user exists in the database
        Session session = MyFactory.getSession();
        Users addedObject = (Users) session.get(Users.class, added.getPhone());
        if (addedObject != null) {
            //checks if the added user has sent a friend request before
            UsersHaveUsers addedCheckObject = (UsersHaveUsers) session.get(UsersHaveUsers.class, new UsersHaveUsersId(added.getPhone(), adder.getPhone()));
            //checks if that frienship status is pending
            if (addedCheckObject != null) {
                //changes this status to accepted and adds a new row for adder
                addedCheckObject.setAcceptanceStatus("accepted");
                Users newAdderObject = new Users();
                newAdderObject.setPhone(adder.getPhone());
                Users newAddedObject = new Users();
                newAddedObject.setPhone(added.getPhone());
                UsersHaveUsers newEntry = new UsersHaveUsers(new UsersHaveUsersId(adder.getPhone(), added.getPhone()), newAdderObject, newAddedObject, "accepted");
                session.beginTransaction();
                session.saveOrUpdate(addedCheckObject);
                session.persist(newEntry);
                session.getTransaction().commit();
            } else {
                //adds a new row for this friend request
                Users newAdderObject = new Users();
                newAdderObject.setPhone(adder.getPhone());
                Users newAddedObject = new Users();
                newAddedObject.setPhone(added.getPhone());
                UsersHaveUsers newEntry = new UsersHaveUsers(new UsersHaveUsersId(adder.getPhone(), added.getPhone()), newAdderObject, newAddedObject, "pending");
                session.beginTransaction();
                session.persist(newEntry);
                session.getTransaction().commit();
            }
        } else {
            userExists = false;
        }
        return userExists;
    }

    @Override
    public List<User> retrieveContacts(User user) throws RemoteException {
//        List<User> friendList = new ArrayList<>();
//        String sql = "SELECT * FROM USERS WHERE PHONE IN (SELECT PHONE_B FROM USERS_HAVE_USERS WHERE PHONE_A = '"
//                + user.getPhone() + "')";
//        try {
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()) {
//                friendList.add(new User(resultSet.getString("PHONE"),
//                        resultSet.getString("FIRST_NAME"),
//                        resultSet.getString("LAST_NAME"),
//                        resultSet.getString("PASSWORD"),
//                        resultSet.getString("EMAIL"),
//                        resultSet.getBytes("PIC"),
//                        GenderEnum.valueOf(resultSet.getString("GENDER").toUpperCase()),
//                        resultSet.getString("COUNTRY"),
//                        resultSet.getString("DATE_OF_BIRTH"),
//                        resultSet.getString("BIO"),
//                        StatusEnum.valueOf(resultSet.getString("STATUS").toUpperCase()),
//                        RegisteredByEnum.valueOf(resultSet.getString("REGISTERED_BY").toUpperCase())));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return friendList;
        return null;
    }

    @Override
    public void delete(User user) throws RemoteException {
//        String sql = "DELETE FROM USERS WHERE PHONE = '" + user.getPhone() + "'";
//        try {
//            statement.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void removeContact(User remover, User removed) throws RemoteException {
//        String sql = "DELETE FROM USERS_HAVE_USERS WHERE (PHONE_A = '" + remover.getPhone()
//                + "' AND PHONE_B = '" + removed.getPhone()
//                + "') OR (PHONE_A = '" + removed.getPhone()
//                + "' AND PHONE_B = '" + remover.getPhone()
//                + "')";
//        try {
//            statement.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public int getOnlineUsers() throws RemoteException {
//        Integer result = null;
//        try {
//            String sql = "select Count(*) from users where status='" + StatusEnum.ONLINE.getStatus(StatusEnum.ONLINE) + "'";
//            ResultSet resultSet = statement.executeQuery(sql);
//            if (resultSet.next()) {
//                result = resultSet.getInt(1);
//            } else {
//                System.err.println("Error happend!System failed to count online users.");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        return result;
        return 0;
    }

    @Override
    public int getOfflineUsers() throws RemoteException {
//        Integer result = null;
//        try {
//            String sql = "select Count(*) from users where status='" + StatusEnum.OFFLINE.getStatus(StatusEnum.OFFLINE) + "'";
//            ResultSet resultSet = statement.executeQuery(sql);
//            if (resultSet.next()) {
//                result = resultSet.getInt(1);
//            } else {
//                System.err.println("Error happend!System failed to count offline users.");
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        return result;
        return 0;
    }

    @Override
    public Map<String, Integer> getGenderStatistics() throws RemoteException {
//        Map<String, Integer> genderStatistics = new HashMap<>();
//        try {
//            String sql = "select Count(phone),gender from users group by gender";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                genderStatistics.put(resultSet.getString(2), resultSet.getInt(1));
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//            ex.printStackTrace();
//        }
//        return genderStatistics;
        return null;
    }

    @Override
    public Map<String, Integer> getCountryStatistics() throws RemoteException {

//        Map<String, Integer> countryStatistics = new HashMap<>();
//        try {
//            String sql = "select Count(phone),country from users group by country";
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                countryStatistics.put(resultSet.getString(2), resultSet.getInt(1));
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return countryStatistics;
        return null;
    }
}
