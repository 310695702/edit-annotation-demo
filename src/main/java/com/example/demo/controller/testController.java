package com.example.demo.controller;

import com.example.demo.annotation.SaveLog;
import com.example.demo.annotation.testInterface;
import com.example.demo.comment.Result;
import com.example.demo.dao.pojo.Test;
import com.example.demo.exception.DemoException;
import com.example.demo.exception.DemoExceptionEnum;
import com.example.demo.service.testService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-07-16 8:37
 **/
@RestController
public class testController {
    @Autowired
    testService testService;

    @PostMapping("/regist")
    public Result regist(@RequestBody Test test) throws DemoException {
        if (notNull(test)){
            testService.insert(test);
            return Result.ok();
        }
        return Result.error(DemoExceptionEnum.ADD_ERROR);
    }

    private boolean notNull(Test test) {
        if (!StringUtils.isEmpty(test.getName())&&!StringUtils.isEmpty(test.getPassword())){
            return true;
        }
        return false;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Test test) throws DemoException {
        String token = testService.login(test);
        return Result.ok(token);
    }

    @GetMapping("/current")
    public Result checkLogin(@testInterface Test test){
        return Result.ok(test);
    }

    @GetMapping("/SaveLog")
    @SaveLog
    public void SaveLog(){
    }
}
