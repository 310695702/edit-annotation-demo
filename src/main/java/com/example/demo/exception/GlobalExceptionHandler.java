package com.example.demo.exception;

import com.example.demo.comment.Result;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-08-03 15:19
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    //private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Object handlerException(Exception e, HttpServletResponse response){
        //log.error("Default Exception: ",e);
        if (e instanceof ExpiredJwtException){
            return Result.error(DemoExceptionEnum.LOGIN_EXPIRED);
        }
        return Result.error(DemoExceptionEnum.SYSTEM_ERROR);
    }

    @ExceptionHandler(DemoException.class)
    public Object handlerWebforumException(DemoException e){
        //log.error("WebforumException: ",e);
        return Result.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handlerMethodException(MethodArgumentNotValidException e){
        //log.error("MethodArgumentNotValidException: ", e);
        return  handleBindingResult(e.getBindingResult());
    }

    private Result  handleBindingResult(BindingResult result){
        List<String> list = null;
        if (result.hasErrors()) {
            List<ObjectError> allerrors = result.getAllErrors();
            for (ObjectError obj:allerrors){
                String msg = obj.getDefaultMessage();
                list.add(msg);
            }
        }
        if (list==null){
            return Result.error(DemoExceptionEnum.REQUEST_PARAM_ERROR);
        }
        return Result
                .error(DemoExceptionEnum.REQUEST_PARAM_ERROR.getCode(), list.toString());
    }
}