package exceptions;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {

        ErrorMessage customMsj = new ErrorMessage( 111, ex.getLocalizedMessage());

        return new ResponseEntity<Object> (customMsj, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    public ResponseEntity<Object> handleInvalidTokenExceptions(InvalidTokenException ex, WebRequest req) {

        ErrorMessage customMsj = new ErrorMessage( ex.getErrorCode(), ex.getMessage());

        return new ResponseEntity<Object> (customMsj, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleValidationExceptions(ValidationException ex, WebRequest req) {

        ErrorMessage customMsj = new ErrorMessage( ex.getErrorCode(), ex.getMessage());

        return new ResponseEntity<Object> (customMsj, HttpStatus.BAD_REQUEST);
    }
}