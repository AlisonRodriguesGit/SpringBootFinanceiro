package br.com.devspring.resources.exception;

import java.util.Map;

//Valida Excepition referente ao BeanValidation
public class ValidationError extends StandardError{

    private String field;
    private String fieldMessage;
    //private Map<String,String> fieldMessageMap;

    public ValidationError(Integer status, String msg, Long timeStamp, String developerMessage, String field, String fieldMessage) {
        super(status, msg, timeStamp, developerMessage);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

    /*public Map<String, String> getFieldMessageMap() {
        return fieldMessageMap;
    }

    public void setFieldMessageMap(Map<String, String> fieldMessageMap) {
        this.fieldMessageMap = fieldMessageMap;
    }*/

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
