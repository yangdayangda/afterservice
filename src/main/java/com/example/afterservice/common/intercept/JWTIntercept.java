package com.example.afterservice.common.intercept;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

import com.example.afterservice.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class JWTIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try{
            JWTUtil.verify(token);
            return true;
        }catch (SignatureVerificationException e){
            map.put("massage","无效签名");
        }catch (TokenExpiredException e){
            map.put("massage","token过期");
        }catch (AlgorithmMismatchException e){
            map.put("massage","算法错误");
        }catch (Exception e){
            map.put("massage","无效签名");
        }
        map.put("result",null);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/jsion;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
