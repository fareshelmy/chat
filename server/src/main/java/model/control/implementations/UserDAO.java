package model.control.implementations;

import model.control.interfaces.IUserDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.entities.User;
import model.enums.FriendshipStatusEnum;
import model.enums.GenderEnum;
import model.enums.RegisteredByEnum;
import model.enums.StatusEnum;

public class UserDAO implements IUserDAO {

    Statement statement;

    public UserDAO(Statement statement) {
        this.statement = statement;
    }

    @Override
    public void persist(User user) {

        String sql = "INSERT INTO USERS (PHONE, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL, PIC, GENDER, COUNTRY, DATE_OF_BIRTH, BIO, STATUS, REGISTERED_BY) VALUES ('"
                + user.getPhone()
                + "', '" + user.getFirstName()
                + "', '" + user.getLastName()
                + "', '" + user.getPassword()
                + "', '" + user.getEmail()
                + "', '" + (user.getPic() == null ? "00" : user.getPic())
                + "', '" + user.getGender()
                + "', '" + user.getCountry()
                + "', '" + (user.getDateOfBirth() == null ? "00" : user.getDateOfBirth())
                + "', '" + user.getBio()
                + "', '" + user.getStatus()
                + "', '" + user.getRegisteredBy() + "')";
        try {
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET FIRST_NAME = '" + user.getFirstName()
                + "', LAST_NAME = '" + user.getLastName()
                + "', PASSWORD = '" + user.getPassword()
                + "', EMAIL = '" + user.getEmail()
                + "', PIC = '" + (user.getPic() == null ? "00" : user.getPic())
                + "', GENDER = '" + user.getGender()
                + "', COUNTRY = '" + user.getCountry()
                + "', DATE_OF_BIRTH = '" + (user.getDateOfBirth() == null ? "00" : user.getDateOfBirth())
                + "', BIO = '" + user.getBio()
                + "', STATUS = '" + user.getStatus()
                + "', REGISTERED_BY = '" + user.getRegisteredBy()
                + "' WHERE PHONE = '" + user.getPhone() + "'";
        try {
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean validate(String phone, String password) {
        boolean validated = false;
        String sql = "SELECT PHONE FROM USERS WHERE PHONE = '" + phone
                + "' AND PASSWORD = '" + password + "'";
        try {
            if (statement.executeQuery(sql).next()) {
                validated = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return validated;
    }

    @Override
    public boolean addContact(User adder, User added) {
        boolean userExists = true;
        //checks if the added user exists in the database
        String checkUserSql = "SELECT PHONE FROM USERS WHERE PHONE = '" + added.getPhone() + "'";
        try {
            if (statement.executeQuery(checkUserSql).next()) {
                //checks if the added user has sent a friend request before
                String checkSql = "SELECT FRIENDSHIP_STATUS FROM USERS_HAVE_USERS WHERE PHONE_A = '" + added.getPhone()
                        + "' AND PHONE_B = '" + adder.getPhone() + "'";

                ResultSet result = statement.executeQuery(checkSql);
                //checks if that frienship status is pending
                if (result.next() && result.getString("FRIENDSHIP_STATUS").equals(FriendshipStatusEnum.PENDING.getFriendshipStatus())) {
                    //changes this status to accepted and adds a new row for adder
                    String updateSql = "UPDATE USERS_HAVE_USERS SET FRIENDSHIP_STATUS = '"
                            + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "' WHERE PHONE_A = '" + added.getPhone()
                            + "' AND PHONE_B = '" + adder.getPhone() + "'";
                    statement.executeQuery(updateSql);

                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.ACCEPTED.getFriendshipStatus()
                            + "')";
                    statement.executeQuery(insertSql);
                } else {
                    //adds a new row for this friend request
                    String insertSql = "INSERT INTO USERS_HAVE_USERS VALUES ('" + adder.getPhone()
                            + "', '" + added.getPhone()
                            + "', '" + FriendshipStatusEnum.PENDING.getFriendshipStatus()
                            + "')";
                    statement.executeQuery(insertSql);
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
    public List<User> retrieveContacts(User user) {
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
                        resultSet.getBlob("PIC"),
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
    public void delete(User user) {
        String sql = "DELETE FROM USERS WHERE PHONE = '" + user.getPhone() + "'";
        try {
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeContact(User remover, User removed) {
        String sql = "DELETE FROM USERS_HAVE_USERS WHERE (PHONE_A = '" + remover.getPhone()
                + "' AND PHONE_B = '" + removed.getPhone()
                + "') OR (PHONE_A = '" + removed.getPhone()
                + "' AND PHONE_B = '" + remover.getPhone()
                + "')";
        try {
            statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
