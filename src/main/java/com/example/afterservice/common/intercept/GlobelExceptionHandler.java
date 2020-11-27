package com.example.afterservice.common.intercept;

import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.ErrorCode;
import com.example.afterservice.common.domain.RestResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobelExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse processExcetion(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception e){

        if( e instanceof BusinessException){
            BusinessException e1 = (BusinessException) e;
            ErrorCode errorCode = e1.getErrorCode();
            int code = errorCode.getCode();
            String desc = errorCode.getDesc();
            return new RestResponse(String.valueOf(code),desc);
        }else if (e instanceof UnauthorizedException){
            return new RestResponse("103102","未授权");
        }
        return new RestResponse(String.valueOf(CommonErrorCode.UNKNOWN.getCode()),CommonErrorCode.UNKNOWN.getDesc());
    }
}
