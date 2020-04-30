package com.example.gateway;

import feign.FeignException;
import org.json.JSONObject;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler extends Throwable implements ErrorController {


    @ExceptionHandler(FeignException.BadRequest.class)
    public Map<String, Object> handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toMap();
    }

    @ExceptionHandler(FeignException.NotFound.class)
    public Map<String, Object> handleFeignStatusException2(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toMap();
    }

    @ExceptionHandler()
    public Map<String, Object> handleFeignStatusException3(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return new JSONObject(e.contentUTF8()).toMap();
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}