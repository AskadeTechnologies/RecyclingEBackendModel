package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/28/2017.
 */
public class AskadeNoEntityFoundException extends Exception {
    private static final String message = "Entity $$ could not be found";

    public AskadeNoEntityFoundException() {
        super();
    }

    public AskadeNoEntityFoundException(String message) {
        super(message);
    }

    public AskadeNoEntityFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AskadeNoEntityFoundException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        System.out.println(super.getMessage());
        String newMessage = message.replace("$$", super.getMessage());
        return newMessage;
    }
}
