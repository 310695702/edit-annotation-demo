package com.example.demo.resolver;

import com.example.demo.annotation.testInterface;
import com.example.demo.comment.Constant;
import com.example.demo.dao.pojo.Test;
import com.example.demo.service.testService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
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
public class testResolver implements HandlerMethodArgumentResolver {
    private final testService testService;

    public testResolver(testService testService){
        this.testService = testService;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(testInterface.class) && methodParameter.getParameterType().equals(Test.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        testInterface testinterface = methodParameter.getParameterAnnotation(testInterface.class);
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String token = request.getHeader("token");
        Test test;
        if (StringUtils.isEmpty(token)){
            return testinterface.value();
        }
        else{
            Claims body = Jwts.parser().setSigningKey(Constant.APP_SECRET).parseClaimsJws(token).getBody();
            test = testService.getUser(Integer.parseInt(body.get("id").toString()));
        }
        return test;
    }
}