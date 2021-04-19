package br.com.devspring.resources.exception;

import br.com.devspring.services.exception.DataIntegrityViolationException;
import br.com.devspring.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Manipulador de exceções do Resource(recursos)
@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler { //A classe ResponseEntityExceptionHandler trata a grande maioria das exception

    //Asinatura padrão do @ControllerAdvice
    @ExceptionHandler(ObjectNotFoundException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException ex, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(),ex.getMessage(), System.currentTimeMillis(), ex.getClass().getName());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), System.currentTimeMillis(), ex.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors(); //Recupera os campos que tiveram falha na validação

        //Percorre a lista, grava em um map o Nome do Field e retorna uma string separando os Fields por virgula.
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Fields Validation Error"
                , System.currentTimeMillis(), ex.getClass().getName(),fields, fieldMessages);

        //Codigo comentado abaixo é a tentativa trabalhar com HaspMap, melhorando a vizualização do retorno do erro. NAO FUNCIONOU.
        /*ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(),"Fields Validation Error"
                , System.currentTimeMillis(), ex.getClass().getName());

        Map<String,String> fielsMenssage = new HashMap<String,String>();
        fielsMenssage.put(fields,fieldMessages);*/

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class) //Indica que esse método trata esse tipo de exception.
    public ResponseEntity<StandardError> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),ex.getMessage(), System.currentTimeMillis(), ex.getClass().getName());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    //ERRO que trata as demais exception
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StandardError err = new StandardError(status.value(),ex.getMessage(), System.currentTimeMillis(), ex.getClass().getName());
        return ResponseEntity.status(status.value()).body(err);

    }

}
