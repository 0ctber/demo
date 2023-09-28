package com.byd.demo.factory;

import com.byd.demo.pojo.Car;
import com.byd.demo.service.CarService;
import com.byd.demo.service.impl.CarServiceImpl;
import com.byd.demo.service.impl.CarServiceImpl2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarFactory {


    public CarService getCarService(Integer vipGrade){
        CarService service;
        switch (vipGrade){
            case 1:
                service = getCarsService1();
                break;
            case 2:service = getCarsService2();
                break;
            default :service = null;
        }
        return service;
    }
    @Bean
    public CarService getCarsService1(){
        return new CarServiceImpl2();
    }
    @Bean
    public CarService getCarsService2(){
        return new CarServiceImpl();
    }
}
