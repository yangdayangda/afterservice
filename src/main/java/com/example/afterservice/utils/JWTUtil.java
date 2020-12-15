package com.example.afterservice.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtil {
//    jwt加密签名
    private static String sign ="fsdfdsfsfsfs";

    /**
     * 通过传入需要加密的内容，得到密文
     * @param map
     * @return
     */
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

    /**
     * 检查token是否正确
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(sign)).build().verify(token);
        return verify;
    }

    /**
     * 传入token和需要获取的值K，得到V值
     * @param token
     * @param Object
     * @return
     */
    public static String getObjectByToken(String token,String Object){
        DecodedJWT verify = JWT.require(Algorithm.HMAC384(sign)).build().verify(token);
        return verify.getClaim(Object).asString();
    }

    /**
     * 返回过期的时间
     *
     * @param jwt
     * @return
     */
    public static Date getExpireAt(String jwt) {
        try {
            DecodedJWT decodedJwt = JWT.decode(jwt);
            return decodedJwt.getExpiresAt();
        } catch (JWTDecodeException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static String getIdByheader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null){
            return null;
        }
        DecodedJWT verify = null;
        try {
            verify = JWT.require(Algorithm.HMAC384(sign)).build().verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String id = verify.getClaim("id").asString();
        return id;
    }

    public static String getNameByheader(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null){
            return null;
        }
        DecodedJWT verify = null;
        try {
            verify = JWT.require(Algorithm.HMAC384(sign)).build().verify(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String name = verify.getClaim("name").asString();
        return name;
    }
}
