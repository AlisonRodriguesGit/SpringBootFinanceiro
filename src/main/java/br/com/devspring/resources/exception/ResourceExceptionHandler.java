package br.com.devspring.resources.exception;

import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

//Manipulador de exceções do Resource(recursos)
@ControllerAdvice
public class ResourceExceptionHandler {

    //Asinatura padrão do @ControllerAdvice
    @ExceptionHandler(ObjectNotFoundException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis(), e.getClass().getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    //Asinatura padrão do @ControllerAdvice
    @ExceptionHandler(MethodArgumentNotValidException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<ValidationError> argumentNotFound(MethodArgumentNotValidException e, HttpServletRequest request){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors(); //Recupera os campos que tiveram falha na validação
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Fields Validation Error", System.currentTimeMillis(), e.getClass().getName(),fields, fieldMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }


}
