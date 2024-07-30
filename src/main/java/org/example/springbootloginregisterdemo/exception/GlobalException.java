package org.example.springbootloginregisterdemo.exception;

import org.example.springbootloginregisterdemo.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.example.springbootloginregisterdemo.exception.ServiceException;

public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Response serviceException(ServiceException e) {
        return Response.err(e.getMessage(),e.getCode());
    }
}
