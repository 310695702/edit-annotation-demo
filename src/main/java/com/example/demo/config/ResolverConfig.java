package com.example.demo.config;

import com.example.demo.resolver.SaveLogResolver;
import com.example.demo.resolver.testResolver;
import com.example.demo.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-07-16 8:34
 **/
@Configuration
public class ResolverConfig implements WebMvcConfigurer {

    @Autowired
    private testService testService;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new testResolver(testService));
        resolvers.add(new SaveLogResolver(testService));
    }
}
