package br.com.devspring.services.exception;

public class DataIntegrityViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DataIntegrityViolationException(String msg){
        super(msg);
    }

    /*public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }*/
}
