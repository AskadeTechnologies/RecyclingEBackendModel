package ro.askade.exception;

/**
 * Created by AdrianIonita on 1/28/2017.
 */
public class AskadeConcurentUpdateException extends Exception {
    private String message = "Concurent update detected. Reload instance and try again.";
    public AskadeConcurentUpdateException() {
        super();
    }

    public AskadeConcurentUpdateException(String message) {
        super(message);
    }

    public AskadeConcurentUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public AskadeConcurentUpdateException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
