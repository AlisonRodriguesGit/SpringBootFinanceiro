package br.com.devspring.services.exception;

public class AuthorizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthorizationException(String msg){
        super(msg);
    }

    /*public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }*/
}
