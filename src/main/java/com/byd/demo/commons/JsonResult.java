package com.byd.demo.commons;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {

    private boolean success = true;
    private int code;//state code
    private String msg;//信息
    private  Object data;//存储信息


    public static JsonResult success(Object data){
        return new JsonResult(true,0,"success",data);
    }
    public static JsonResult fail(Integer code,String msg){
        return new JsonResult(false,code,msg,null);
    }
}
