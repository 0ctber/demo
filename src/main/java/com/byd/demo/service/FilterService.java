package com.byd.demo.service;

import com.byd.demo.commons.JsonResult;
import com.byd.demo.pojo.DemoTableOne;
import com.github.pagehelper.PageInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;



public interface FilterService{

    PageInfo selectByUsername(int pageNum, int pageSize, String username);
}
