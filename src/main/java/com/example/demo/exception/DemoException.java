package com.example.demo.exception;

import lombok.Getter;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-08-03 10:23
 **/
@Getter
public class DemoException extends Exception{

    private final Integer code;
    private final String message;

    public DemoException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public DemoException(DemoExceptionEnum exceptionEnum){
        this(exceptionEnum.getCode(),exceptionEnum.getMessage());
    }

}