package com.byd.demo.dao;

import com.byd.demo.pojo.DemoTableOne;

import java.util.List;

public interface FilterServiceDao{
    List<DemoTableOne> selectByUsername(int pageNum,int pageSize,String username);
}
