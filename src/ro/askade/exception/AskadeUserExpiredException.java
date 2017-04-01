package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/28/2017.
 */
public class AskadeUserExpiredException extends Exception {
    private static final String excMessage = "User %TOKEN% is expired";

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    private String userName;

    public static String getExcMessage() {
        return excMessage;
    }

    public AskadeUserExpiredException() {
        super();
    }

    public AskadeUserExpiredException(String message) {
        super(message);
    }

    public AskadeUserExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AskadeUserExpiredException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        String newMessage = excMessage.replace("%TOKEN%", userName);
        return newMessage;
    }
}
