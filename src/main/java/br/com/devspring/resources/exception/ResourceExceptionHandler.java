package br.com.devspring.resources.exception;

import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

//Manipulador de exceções do Resource(recursos)
@ControllerAdvice
public class ResourceExceptionHandler {

    //Asinatura padrão do @ControllerAdvice
    @ExceptionHandler(ObjectNotFoundException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
