package controller.implementations;

import com.chat.common.User;
import model.control.implementations.UserDAOHandler;

/**
 *
 * @author yasmin
 */
public class Controller {

    UserDAOHandler userDAOHandler;
    public Controller(){
        userDAOHandler = new UserDAOHandler();
    }
    public void persistUser(User user) {
        userDAOHandler.persistUser(user);
    }

    public void signInUser(User user) {
        userDAOHandler.signInUser(user);
    }
    
}
