package com.example.demo.response;

import com.example.demo.models.Employer;

import java.util.Optional;

// Generic class
public class CustomResponse {
    private int errorCode;
    private int statusCode;
    private String message;
    private Object object;

    public CustomResponse() {}

    public CustomResponse(int errorCode, int statusCode, String message, Object object) {
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
        this.object = object;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
