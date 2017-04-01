package ro.askade.smartRecyle.dao;
import ro.askade.exception.*;
import ro.askade.smartRecycle.model.User;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 3/8/2017.
 */
public interface UserDao {
    public String BEAN_ID = "userDAO";
    public void addUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserExpiredException, AskadeUserNotAvailableYetEx;
    public void updateUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserExpiredException, AskadeUserNotAvailableYetEx, AskadeConcurentUpdateException;
    public List<User> listUsers();
    public User getUserByCode(String userCode);
    public void removePerson(int id);
    public User getUserById(BigInteger userId);

    public User loginUser(User user) throws AskadeUserExpiredException, AskadeUserNotAvailableYetEx, AskadeIncorrectPasswordException;
}
