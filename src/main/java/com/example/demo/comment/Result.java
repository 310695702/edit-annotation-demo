package com.example.demo.comment;

import com.example.demo.exception.DemoExceptionEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-08-03 9:51
 **/
@Getter
@Setter
@ToString
public class Result<E> implements Serializable {

    private Integer code;
    private String message;
    private E data;

    private final static Integer OK = 200;
    private final static String OK_MSG = "success";

    public Result(){
        this(OK, OK_MSG);
    }

    public Result(Integer code,String message,E data){
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Result(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public static <E> Result<E> ok() {
        return new Result<>();
    }

    public static <E> Result<E> ok(E data){
        Result result = new Result<>();
        result.setData(data);
        return result;
    }

    public static <E> Result<E> error(Integer code,String message){
        return new Result<>(code,message);
    }

    public static <E> Result<E> error(DemoExceptionEnum ex){
        return new Result<>(ex.getCode(),ex.getMessage());
    }

}