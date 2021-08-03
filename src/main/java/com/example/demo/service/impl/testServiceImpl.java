package com.example.demo.service.impl;

import com.example.demo.dao.pojo.Test;
import com.example.demo.exception.DemoException;
import com.example.demo.exception.DemoExceptionEnum;
import com.example.demo.service.testService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.mapper.*;
import tk.mybatis.mapper.entity.Example;

/**
 * @version: V1.0
 * @author: HanYuXing
 * @date: 2021-07-16 8:39
 **/
@Service
public class testServiceImpl implements testService {

    @Autowired
    TestMapper testMapper;

    @Override
    public void insert(Test test) throws DemoException {
        int count = testMapper.insertSelective(test);
        if (count == 0)
            throw new DemoException(DemoExceptionEnum.ADD_ERROR);
    }

    @Override
    public String login(Test test) throws DemoException {
        Example example = new Example(Test.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",test.getName());
        criteria.andEqualTo("password",test.getPassword());
        Test result = testMapper.selectOneByExample(example);
        if (result == null){
            throw new DemoException(DemoExceptionEnum.LOGIN_FAILED);
        }
        String token = JwtUtil.getJwtToken(result.getId().toString(),result.getName());
        return token;
    }

    @Override
    public Test getUser(Integer id) {
        Test test = testMapper.selectByPrimaryKey(id);
        test.setPassword(null);
        return test;
    }
}