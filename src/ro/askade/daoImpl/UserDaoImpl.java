package ro.askade.daoImpl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ro.askade.exception.*;
import ro.askade.smartRecycle.model.User;
import ro.askade.smartRecyle.dao.UserDao;
import ro.askade.utils.GenericDaoImpl;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by AdrianIonita on 3/8/2017.
 */
@Repository(value = UserDao.BEAN_ID)
public class UserDaoImpl extends GenericDaoImpl implements UserDao {
    /**
     *
     */
    public SessionFactory sessionFactory;

    /**
     * @param sessionFactory
     */
    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    /**
     * @param sf
     */
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }

    /**
     * @return
     */
    public SessionFactory getSessionFactory(){ return this.sessionFactory;}

    /**
     * @param currentUser
     * @param user
     * @throws AskadeNoUserDefinedException
     * @throws AskadeUserExpiredException
     * @throws AskadeUserNotAvailableYetEx
     */
    @Override
    public void addUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserExpiredException, AskadeUserNotAvailableYetEx {
        addObject(this.sessionFactory, currentUser, user);
    }

    /**
     * @param currentUser
     * @param user
     * @throws AskadeNoUserDefinedException
     * @throws AskadeUserExpiredException
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeConcurentUpdateException
     */
    @Override
    public void updateUser(String currentUser, User user) throws AskadeNoUserDefinedException, AskadeUserExpiredException, AskadeUserNotAvailableYetEx, AskadeConcurentUpdateException {

    }

    /**
     * @return
     */
    @Override
    public List<User> listUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User");
        return query.list();
    }

    /**
     * @param userCode
     * @return
     */
    @Override
    public User getUserByCode(String userCode) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User" + " where user_name = :user_name");
        query.setParameter("user_name",userCode);
        List<User> users = query.list();

        if(users != null && users.size() > 0) {

            return users.get(0);
        }else{
            return null;
        }

    }

    /**
     * @param id
     */
    @Override
    public void removePerson(int id) {

    }

    /**
     * @param userId
     * @return
     */
    @Override
    public User getUserById(BigInteger userId) {
        return (User) getObjectById(this.sessionFactory, User.class, userId);
    }

    /**
     * @param user
     * @return
     * @throws AskadeUserExpiredException
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeIncorrectPasswordException
     */
    @Override
    public User loginUser(User user) throws AskadeUserExpiredException, AskadeUserNotAvailableYetEx, AskadeIncorrectPasswordException {
        User askadeUser = checkUserExists(this.sessionFactory, user.getUserName());
        if (askadeUser != null){
            if(askadeUser.getPassword().equals(user.getPassword())){
                return askadeUser;
            }else{
                throw new AskadeIncorrectPasswordException();
            }
        }
        return null;
    }
}
