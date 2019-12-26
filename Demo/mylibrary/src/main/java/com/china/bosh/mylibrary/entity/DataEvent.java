package com.china.bosh.mylibrary.entity;

/**
 * @author lzq
 * @date 2018/7/15
 */

public class DataEvent<T> {

    private String eventCode;
    private T result;
    private boolean isSuccess;
    private String errorMessage;
    private String errorCode;

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "eventCode:"+eventCode+",result:"+result.toString()+",isSuccess:"+isSuccess+",errorMessage:"+
                errorMessage+",errorCode:"+errorCode;
    }
}
