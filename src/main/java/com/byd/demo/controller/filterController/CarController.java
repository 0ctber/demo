package com.byd.demo.controller.filterController;

import com.byd.demo.commons.JsonResult;
import com.byd.demo.factory.CarFactory;
import com.byd.demo.pojo.Car;
import com.byd.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarFactory carFactory;
    @GetMapping("/discount")
    public JsonResult getPrice(@RequestParam int vipCount){
        CarService carService = carFactory.getCarService(vipCount);
        Car discount = carService.discount(vipCount);
        return JsonResult.success(discount);
    }

}
