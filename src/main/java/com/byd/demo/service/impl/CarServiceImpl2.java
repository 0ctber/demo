package com.byd.demo.service.impl;

import com.byd.demo.pojo.Car;
import com.byd.demo.service.CarService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarServiceImpl2 implements CarService {
    Car car = new Car(0,new BigDecimal(100));
    @Override
    public Car discount(int vipGrade) {
        if (vipGrade == 1){

            car.setVipGrade(vipGrade);
            car.setVipPrice(car.getPrice().multiply(new BigDecimal("0.5")));
            return car;
        }else return car;
    }
}
