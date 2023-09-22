package main.exceptions;

import java.util.Date;

public class ErrorMessage {

    private Date horaError;
    private int codigoError;
    private String mensaje;


    public ErrorMessage(int codError, String errorMessage) {
        super();
        this.horaError = new Date();
        this.codigoError = codError;
        this.mensaje = errorMessage;
    }

    public Date getHoraError() {
        return horaError;
    }
    public void setHoraError(Date horaError) {
        this.horaError = horaError;
    }
    public int getCodigoError() {
        return codigoError;
    }
    public void setCodigoError(int codigo) {
        this.codigoError = codigo;
    }
    public String getErrorMessage() {
        return mensaje;
    }
    public void setErrorMessage(String errorMessage) {
        mensaje = errorMessage;
    }

}
