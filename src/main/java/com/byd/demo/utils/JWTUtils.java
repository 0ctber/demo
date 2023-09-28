package com.byd.demo.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

public class JWTUtils {
    private static final String jwtToken = "jigechen###";
    public static String createToken(Long userId){
        Map<String,Object>  claims = new HashMap<>();
        claims.put("UserId",userId);
        JWT jwt = JWT.create();
        jwt.setKey(jwtToken.getBytes());
        jwt.addPayloads(claims);
        String sign = jwt.sign();
        return sign;
    }

    public static Integer parseToken(String token){
        JWT jwt = JWTUtil.parseToken(token);
        Integer payload = (Integer)jwt.getPayload("UserId");
        return payload;
    }
}
