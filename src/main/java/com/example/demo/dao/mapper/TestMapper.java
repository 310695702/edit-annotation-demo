package com.example.demo.dao.mapper;

import com.example.demo.dao.pojo.Test;
import com.example.demo.my.mapper.MyMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends MyMapper<Test> {
}