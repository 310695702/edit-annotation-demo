package com.example.demo.service;

import com.example.demo.dao.pojo.Test;
import com.example.demo.exception.DemoException;

public interface testService {
    void insert(Test test) throws DemoException;

    String login(Test test) throws DemoException;

    Test getUser(Integer id);
}
