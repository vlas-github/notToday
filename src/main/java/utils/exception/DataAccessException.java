package utils.exception;

/**
 * Created by vlasov-id-131216 on 14.02.15.
 */
public class DataAccessException extends RuntimeException {
    private static final long serialVersionUID = 789263757562412047L;

    public DataAccessException() {
        super();
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
