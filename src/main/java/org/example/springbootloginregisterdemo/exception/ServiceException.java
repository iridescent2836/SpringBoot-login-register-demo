package org.example.springbootloginregisterdemo.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    String code;


    public ServiceException(String message) {
        super(message);
        this.code = "500";
    }

    public ServiceException( String message,String code) {
        super(message);
        this.code = code;
    }
}
