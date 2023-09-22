package main.exceptions;

public class InvalidTokenException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private int errorCode;

    public InvalidTokenException() {
        super("INVALID TOKEN");
        setErrorCode(111);

    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


}
