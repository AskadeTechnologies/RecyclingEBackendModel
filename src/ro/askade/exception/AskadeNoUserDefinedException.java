package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/28/2017.
 */
public class AskadeNoUserDefinedException extends Exception{
    private String message = "Current user is missing. Entity cannot be updated or created";
    public AskadeNoUserDefinedException() {
        super();
    }

    public AskadeNoUserDefinedException(String message) {
        super(message);
    }

    public AskadeNoUserDefinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AskadeNoUserDefinedException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
