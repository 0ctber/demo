package com.byd.demo.service.impl;

import com.byd.demo.annotation.TestAnnotation;
import com.byd.demo.commons.JsonResult;
import com.byd.demo.dao.FilterServiceDao;
import com.byd.demo.pojo.DemoTableOne;
import com.byd.demo.schedule.TaskUtils;
import com.byd.demo.service.FilterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FilterServiceImpl implements FilterService {
    @Autowired
    private FilterServiceDao filterServiceDao;

    @TestAnnotation
    @Override
    public PageInfo selectByUsername(int pageNum, int pageSize, String username) {
        PageHelper.startPage(pageNum,pageSize);
        List<DemoTableOne> demoTableOnes = filterServiceDao.selectByUsername(pageNum, pageSize, username);
        TaskUtils.doTask();
        return new PageInfo(demoTableOnes);//分页结果填入pageinfo
    }
}
