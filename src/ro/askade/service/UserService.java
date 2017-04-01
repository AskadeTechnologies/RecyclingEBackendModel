package ro.askade.service;

import ro.askade.exception.*;
import ro.askade.smartRecycle.model.User;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 3/8/2017.
 */
public interface UserService {
    public void addUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserNotAvailableYetEx, AskadeUserExpiredException;
    public void updateUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserNotAvailableYetEx, AskadeUserExpiredException, AskadeConcurentUpdateException;
    public List<User> listUsers();
    public User getUserByCode(String userCode);
    public void removePerson(int id);
    public User getUserById(BigInteger userId);

    public User loginUser(User user) throws AskadeUserNotAvailableYetEx, AskadeUserExpiredException, AskadeIncorrectPasswordException;
}
