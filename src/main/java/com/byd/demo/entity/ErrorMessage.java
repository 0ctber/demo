package com.byd.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class ErrorMessage {
    @Excel(name = "错误信息")
    private String errorMessage;
}
