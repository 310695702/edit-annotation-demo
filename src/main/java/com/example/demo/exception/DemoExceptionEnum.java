package com.example.demo.exception;

import lombok.Getter;

@Getter
public enum DemoExceptionEnum {

    LOGIN_EXPIRED(997,"登录过期"),
    REQUEST_PARAM_ERROR(998,"参数异常"),
    LOGIN_FAILED(999,"登录失败"),
    ADD_ERROR(1000, "新增失败"),
    SYSTEM_ERROR(20000, "系统错误");

    private final Integer code;
    private final String message;

    private DemoExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
