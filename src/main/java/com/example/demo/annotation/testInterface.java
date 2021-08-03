package com.example.demo.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface testInterface {
    /**
     * 定义注解的一个元素 并给定默认值
     * @return
     */
    String value() default "登录过期";

    int code() default 200;
}
