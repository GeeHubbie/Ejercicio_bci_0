package exceptions;

public class ValidationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private int errorCode;

    public ValidationException(String message, int code) {

        super(message);
        setErrorCode(code);

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
