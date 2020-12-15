package com.example.afterservice.common.intercept;

import com.example.afterservice.shiro.token.JwtToken;
import com.example.afterservice.utils.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 进来的请求到这个进行验证，返回true则放行，false进入下面的onAccessDenied
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
//    处理jwt进行身份认证
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        // 判断token是否存在
        if (token == null) {
            return false;
        }
        JwtToken jwtToken = new JwtToken(token);
        try{
            getSubject(request, response).login(jwtToken);
        } catch (AuthenticationException e){
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




    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


    /**
     * 登录成功后判断是否需要刷新token
     * 登录成功说明：jwt有效，尚未过期。当离过期时间不足一天时，往响应头中放入新的token返回给前端
     *
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
                                     ServletRequest request, ServletResponse response)throws Exception {

        String oldToken = (String) token.getPrincipal();

        Date expireAt = JWTUtil.getExpireAt(oldToken);

        Long time = 1000*60*60*24L;
        long surplusTime = expireAt.getTime()-System.currentTimeMillis();

        if (surplusTime<time) {  // 如果离过期时间不足一天
            String id = JWTUtil.getObjectByToken(oldToken,"id");
            HashMap<String, String> map = new HashMap<>();
            map.put("id",id);
            String newToken = JWTUtil.getToken(map);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("Authrization", newToken);
        }

        return true;
    }

}
