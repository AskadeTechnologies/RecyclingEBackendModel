package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/30/2017.
 */
public class AskadeIncorrectPasswordException extends Exception {
    private static final String errMessage = "Incorrect password";

    @Override
    public String getMessage() {
        return errMessage;
    }
}
