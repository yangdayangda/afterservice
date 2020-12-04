package com.example.afterservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtil {
    private static String sign ="fsdfdsfsfsfs";


    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DAY_OF_MONTH,3);//设置三天以后过期
        JWTCreator.Builder builder = JWT.create();
        map.forEach((K,V)->{
            builder.withClaim(K,V);
        });
        builder.withExpiresAt(instance.getTime());
        String token = builder.sign(Algorithm.HMAC384(JWTUtil.sign));
        return token;
    }

    public static DecodedJWT verify(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(sign)).build().verify(token);
        return verify;
    }


}
