package br.com.devspring.resources.exception;

public class ValidationError extends StandardError{

    private String field;
    private String fieldMessage;

    public ValidationError(Integer status, String msg, Long timeStamp, String developerMessage, String field, String fieldMessage) {
        super(status, msg, timeStamp, developerMessage);
        this.field = field;
        this.fieldMessage = fieldMessage;
    }

    public String getField() {
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
    }
}
