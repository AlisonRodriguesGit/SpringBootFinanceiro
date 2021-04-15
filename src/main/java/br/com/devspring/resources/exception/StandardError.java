package br.com.devspring.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer status;
    private String msg;
    private Long timeStamp;
    private String developerMessage;

    public StandardError(Integer status, String msg, Long timeStamp, String developerMessage) {
        this.status = status;
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.developerMessage = developerMessage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
