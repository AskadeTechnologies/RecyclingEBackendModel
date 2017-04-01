package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/28/2017.
 */
public class AskadeUserNotAvailableYetEx extends Exception {
    private static final String excMessage = "User %TOKEN% is not available yet";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;


    public AskadeUserNotAvailableYetEx() {
        super();
    }

    public AskadeUserNotAvailableYetEx(String message) {
        super(message);
    }

    public AskadeUserNotAvailableYetEx(String message, Throwable cause) {
        super(message, cause);
    }

    public AskadeUserNotAvailableYetEx(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        String newMessage = excMessage.replace("%TOKEN%", userName);
        return newMessage;
    }
}
