package com.example.demo.resolver;

import com.example.demo.annotation.SaveLog;
import com.example.demo.dao.pojo.Test;
import com.example.demo.service.testService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-07-16 8:44
 **/
@Component
public class SaveLogResolver implements HandlerMethodArgumentResolver {

    private final testService testService;

    public SaveLogResolver(testService testService){
        this.testService = testService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(SaveLog.class) && methodParameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        SaveLog saveLog = methodParameter.getParameterAnnotation(SaveLog.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Test test = new Test();
        test.setName(request.getMethod());
        test.setPassword(request.getRequestURI());
        testService.insert(test);
        return null;
    }

}
