package com.byd.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    final BigDecimal price = BigDecimal.valueOf(100);
    int vipGrade;
    BigDecimal vipPrice;
}
