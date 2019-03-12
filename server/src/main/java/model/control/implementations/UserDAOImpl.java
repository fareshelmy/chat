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
import java.util.Iterator;
import javax.sql.rowset.CachedRowSet;
import org.hibernate.Criteria;
import org.hibernate.Query;
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

                Users newAdderObject = new Users(adder.getPhone(),
                        adder.getFirstName(),
                        adder.getLastName(),
                        adder.getPassword(),
                        adder.getEmail(),
                        adder.getPic(),
                        adder.getGenderEnum(),
                        adder.getCountry(),
                        adder.getDateOfBirth(),
                        adder.getBio(),
                        adder.getStatusEnum(),
                        adder.getRegisteredByEnum(), new HashSet(0), new HashSet(0));

                Users newAddedObject = new Users(added.getPhone(),
                        added.getFirstName(),
                        added.getLastName(),
                        added.getPassword(),
                        added.getEmail(),
                        added.getPic(),
                        added.getGenderEnum(),
                        added.getCountry(),
                        added.getDateOfBirth(),
                        added.getBio(),
                        added.getStatusEnum(),
                        added.getRegisteredByEnum(), new HashSet(0), new HashSet(0));

                UsersHaveUsers newEntry = new UsersHaveUsers(new UsersHaveUsersId(adder.getPhone(), added.getPhone()), newAdderObject, newAddedObject, "accepted");
                session.beginTransaction();
                session.saveOrUpdate(addedCheckObject);
                session.persist(newEntry);
                session.getTransaction().commit();
            } else {
                //adds a new row for this friend request
                
                Users newAdderObject = new Users(adder.getPhone(),
                        adder.getFirstName(),
                        adder.getLastName(),
                        adder.getPassword(),
                        adder.getEmail(),
                        adder.getPic(),
                        adder.getGenderEnum(),
                        adder.getCountry(),
                        adder.getDateOfBirth(),
                        adder.getBio(),
                        adder.getStatusEnum(),
                        adder.getRegisteredByEnum(), new HashSet(0), new HashSet(0));

                Users newAddedObject = new Users(added.getPhone(),
                        added.getFirstName(),
                        added.getLastName(),
                        added.getPassword(),
                        added.getEmail(),
                        added.getPic(),
                        added.getGenderEnum(),
                        added.getCountry(),
                        added.getDateOfBirth(),
                        added.getBio(),
                        added.getStatusEnum(),
                        added.getRegisteredByEnum(), new HashSet(0), new HashSet(0));

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

        Session session = MyFactory.getSession();
        session.beginTransaction();

        Users userObject = (Users) session.load(Users.class, user.getPhone());

        List<Users> contacts = session.createCriteria(UsersHaveUsers.class)       
                    .add(Restrictions.eq("usersByPhoneA", userObject))      
                    .setProjection(Projections.projectionList()
                            .add(Projections.property("usersByPhoneB"))           
                    ).list();

        List<User> friendList = new ArrayList<>();

        for (Users contact : contacts) {

            friendList.add(new User(contact.getPhone(),
                    contact.getFirstName(), contact.getLastName(),
                    contact.getPassword(), contact.getEmail(),
                    contact.getPic(),
                    GenderEnum.valueOf(contact.getGender().toUpperCase()),
                    contact.getCountry(), contact.getDateOfBirth(),
                    contact.getBio(),
                    StatusEnum.valueOf(contact.getStatus().toUpperCase()),
                    RegisteredByEnum.valueOf(contact.getRegisteredBy().toUpperCase())));
        }
        
        session.close();

        return friendList;

    }

    @Override
    public void delete(User user) throws RemoteException {
    	Session session = MyFactory.getSession();
        session.beginTransaction();
    	User userObject = (User) session.load(User.class, user.getPhone());

        String queryString = "DELETE FROM USERS WHERE PHONE = :phone";
        Query result = session.createQuery(queryString).setEntity("phone", userObject);
        session.delete("User", user);
        session.close();
//        String sql = "DELETE FROM USERS WHERE PHONE = '" + user.getPhone() + "'";
//        try {
//            statement.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void removeContact(User remover, User removed) throws RemoteException {
    	Session session = MyFactory.getSession();
        session.beginTransaction();
        
      String sql = "DELETE FROM USERS_HAVE_USERS WHERE (PHONE_A = '" + remover.getPhone()
                + "' AND PHONE_B = '" + removed.getPhone()
                + "') OR (PHONE_A = '" + removed.getPhone()
                + "' AND PHONE_B = '" + remover.getPhone()
                + "')";
      session.createSQLQuery(sql);
      session.getTransaction().commit();
      session.close();
//        try {
//            statement.executeUpdate(sql);
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public int getOnlineUsers() throws RemoteException {
        
        Session session = MyFactory.getSession();
        session.beginTransaction();

        Criteria onlineUsers = session.createCriteria(Users.class,"u");
        onlineUsers.add(Restrictions.eq("u.status", StatusEnum.ONLINE.toString()));

        Integer users = onlineUsers.list().size();
        
        session.close();
        
        return users;
        
    }

    @Override
    public int getOfflineUsers() throws RemoteException {

        Session session = MyFactory.getSession();
        session.beginTransaction();

        Criteria offlineUsers = session.createCriteria(Users.class,"u");
        offlineUsers.add(Restrictions.eq("u.status", StatusEnum.OFFLINE.toString()));

        Integer users = offlineUsers.list().size();
        
        session.close();
        
        return users;
        
    }

    @Override
    public Map<String, Integer> getGenderStatistics() throws RemoteException {
       
        Map<String, Integer> genderStatistics = new HashMap<>();
        
        Session session = MyFactory.getSession();
        session.beginTransaction();

        Criteria maleUsers = session.createCriteria(Users.class,"u");
        maleUsers.add(Restrictions.eq("u.gender", GenderEnum.MALE.toString()));
        Integer maleCount = maleUsers.list().size();
        
        Criteria femaleUsers = session.createCriteria(Users.class,"u");
        femaleUsers.add(Restrictions.eq("u.gender", GenderEnum.FEMALE.toString()));
        Integer femaleCount = femaleUsers.list().size();
        
        session.close();
        
        genderStatistics.put(GenderEnum.MALE.toString(), maleCount);
        genderStatistics.put(GenderEnum.FEMALE.toString(), femaleCount);
        
        return genderStatistics;
        
    }

    @Override
    public Map<String, Long> getCountryStatistics() throws RemoteException {
        
        Session session = MyFactory.getSession();
        session.beginTransaction();

        Map<String, Long> countryStatistics = new HashMap<>();
        
        List result = session.createCriteria(Users.class)       
//                    .add(Restrictions.ge("someColumn", xxxxx))      
                    .setProjection(Projections.projectionList()
                            .add(Projections.groupProperty("country"))
                            .add(Projections.count("country"))           
                    ).list();
        
        Iterator iter = result.iterator();
        while (iter.hasNext())
        {
            System.out.println("New object");
            Object[] obj = (Object[]) iter.next();
            System.out.println(obj[0]+" "+obj[1]);
            countryStatistics.put((String) obj[0],(Long) obj[1]);
        }
        
        return countryStatistics;
        
    }
}
