package utils.exception;

/**
 * Created by vlasov-id-131216 on 15.02.15.
 */
public class BusinessException extends Exception {

    public BusinessException() {

    }

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(Exception e) {
        super(e);
    }
}
