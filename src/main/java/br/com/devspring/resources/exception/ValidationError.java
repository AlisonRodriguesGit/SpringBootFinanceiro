package br.com.devspring.resources.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Valida Excepition referente ao BeanValidation
public class ValidationError extends StandardError{

    //private String field;
    //private String fieldMessage;
    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp, String developerMessage/*, String field, String fieldMessage*/) {
        super(status, msg, timeStamp, developerMessage);
        //this.field = field;
        //this.fieldMessage = fieldMessage;
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void setErros(List<FieldMessage> erros) {
        this.erros = erros;
    }

    public void addError(String fieldName, String messagem){
        erros.add(new FieldMessage(fieldName,messagem));
    }

        /* public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    public void setFieldMessage(String fieldMessage) {
        this.fieldMessage = fieldMessage;
    }*/
}
