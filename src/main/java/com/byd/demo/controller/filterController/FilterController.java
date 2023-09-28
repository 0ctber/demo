package com.byd.demo.controller.filterController;


import com.byd.demo.commons.JsonResult;
import com.byd.demo.pojo.DemoTableOne;
import com.byd.demo.service.FilterService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {
    @Autowired
    private FilterService filterService;
    @GetMapping("/selectByUsername")
    public JsonResult selectByUsername(@RequestParam int pageNum,@RequestParam int pageSize,@RequestParam String username){
        PageInfo demoTableOnes = filterService.selectByUsername(pageNum,pageSize,username);
        return JsonResult.success(demoTableOnes);
    }
}
