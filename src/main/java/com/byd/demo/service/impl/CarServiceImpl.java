package com.byd.demo.service.impl;

import com.byd.demo.pojo.Car;
import com.byd.demo.service.CarService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CarServiceImpl implements CarService {
    @Override
    public Car discount(int vipGrade) {
        Car car = new Car(0,new BigDecimal(100));
        if (vipGrade == 2){
            car.setVipPrice(car.getPrice().multiply(new BigDecimal("0.8")));
            car.setVipGrade(vipGrade);
        return car;
        }else return car;
    }
}