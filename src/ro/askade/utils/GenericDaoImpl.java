package ro.askade.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ro.askade.daoImpl.UserDaoImpl;
import ro.askade.exception.AskadeConcurentUpdateException;
import ro.askade.exception.AskadeNoUserDefinedException;
import ro.askade.exception.AskadeUserExpiredException;
import ro.askade.exception.AskadeUserNotAvailableYetEx;
import ro.askade.smartRecycle.model.User;
import java.lang.reflect.Field;
import java.math.BigInteger;

/**
 * Created by AdrianIonita on 3/10/2017.
 */
public class GenericDaoImpl<T> {

    /**
     * @param sessionFactory
     * @param currentUser
     * @return
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeUserExpiredException
     */
    public User checkUserExists(SessionFactory sessionFactory, String currentUser) throws AskadeUserNotAvailableYetEx, AskadeUserExpiredException {
        boolean result = false;
        UserDaoImpl askadeUserDAO = new UserDaoImpl(sessionFactory);
        User askadeUser = askadeUserDAO.getUserByCode(currentUser);
        if (askadeUser != null){
            if (askadeUser.getDataIn() != null){
                if(askadeUser.getDataIn().compareTo(AskadeUtils.getCurrentDate()) <= 0){
                    result = true;
                }else{
                    AskadeUserNotAvailableYetEx exception = new AskadeUserNotAvailableYetEx();
                    exception.setUserName(currentUser);
                    throw exception;
                }
            }
            if(result){
                if(askadeUser.getDataOut() != null){
                    if(askadeUser.getDataOut().compareTo(AskadeUtils.getCurrentDate()) > 0){
                        result = true;
                    }else{
                        AskadeUserExpiredException exception = new AskadeUserExpiredException();
                        exception.setUserName(currentUser);
                        throw exception;
                    }
                }
            }
        }
        return askadeUser;
    }

    /**
     * @param sessionFactory
     * @param currentUser
     * @param object
     * @return
     * @throws AskadeNoUserDefinedException
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeUserExpiredException
     */
    public Object addWhoColumns(SessionFactory sessionFactory, String currentUser, Object object) throws AskadeNoUserDefinedException, AskadeUserNotAvailableYetEx, AskadeUserExpiredException  {
        if(currentUser == null || currentUser.isEmpty() || checkUserExists(sessionFactory, currentUser) != null){
            throw new AskadeNoUserDefinedException();
        }
        try {
            for(Field field : object.getClass().getDeclaredFields()){
                boolean isAccessible = false;
                if(!field.isAccessible()) {
                    field.setAccessible(true);
                    isAccessible = true;
                }
                if(field.getName().equals(AskadeUtils.CREATED_BY)){
                    if (field.get(object) == null) {
                        field.set(object, currentUser);
                    }
                }
                if(field.getName().equals(AskadeUtils.LAST_UPDATED_BY)){
                    field.set(object, currentUser);
                }
                if(isAccessible){
                    field.setAccessible(false);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * @param sessionFactory
     * @param currentUser
     * @param object
     * @throws AskadeNoUserDefinedException
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeUserExpiredException
     */
    public void addObject(SessionFactory sessionFactory, String currentUser, Object object) throws AskadeNoUserDefinedException, AskadeUserNotAvailableYetEx, AskadeUserExpiredException {
        Session session = sessionFactory.getCurrentSession();
        session.persist(addWhoColumns(sessionFactory, currentUser,object));
    }

    /**
     * @param sessionFactory
     * @param currentUser
     * @param object
     * @throws AskadeNoUserDefinedException
     * @throws AskadeUserNotAvailableYetEx
     * @throws AskadeUserExpiredException
     * @throws AskadeConcurentUpdateException
     */
    public void updateObject(SessionFactory sessionFactory, String currentUser, Object object) throws AskadeNoUserDefinedException, AskadeUserNotAvailableYetEx, AskadeUserExpiredException, AskadeConcurentUpdateException {
        Session session = sessionFactory.getCurrentSession();
        session.update(addWhoColumns(sessionFactory, currentUser, object));
    }

    /**
     * @param sessionFactory
     * @param clazz
     * @param id
     * @return
     */
    public Object getObjectById(SessionFactory sessionFactory, Class<T> clazz, BigInteger id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(clazz, id);
    }
}
