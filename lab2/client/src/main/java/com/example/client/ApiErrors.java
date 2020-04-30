package com.example.client;

import org.springframework.http.HttpStatus;


public class ApiErrors{
    private HttpStatus status;
    private String message;
    private Integer code;


    public ApiErrors(HttpStatus status, String message, Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
