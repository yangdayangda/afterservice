package com.example.afterservice.common.intercept;

import com.example.afterservice.shiro.token.JwtToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        log.info("获取到的token是:{}",token);
        // 判断token是否存在
        if (token == null) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        try{
            log.info("提交UserModularRealmAuthenticator决定由哪个realm执行操作...");
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e){
            log.info("捕获到身份认证异常");
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        map.put("Code","101010");
        map.put("Massage","身份认证失败");
        map.put("Result",null);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/jsion;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
