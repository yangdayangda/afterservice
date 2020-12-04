package com.example.afterservice.common.intercept;

import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.ErrorCode;
import com.example.afterservice.common.domain.RestResponse;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobelExceptionHandler {

    /**
     *全局异常处理类，根据不同的异常类型返回不同信息
     * @param request
     * @param response
     * @param e
     * @return
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestResponse processExcetion(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception e){

//        处理已知的异常
        if( e instanceof BusinessException){
            BusinessException e1 = (BusinessException) e;
            ErrorCode errorCode = e1.getErrorCode();
            int code = errorCode.getCode();
            String desc = errorCode.getDesc();
            return new RestResponse(String.valueOf(code),desc);
        }else if (e instanceof UnauthorizedException){
            return new RestResponse("103102","未授权");
        }else if(e instanceof BindException){
            return new RestResponse("100020"
                    ,((BindException) e).getBindingResult().getFieldError().getDefaultMessage());
        }

//        未处理的异常全部到这里
        return new RestResponse(String.valueOf(CommonErrorCode.UNKNOWN.getCode()),CommonErrorCode.UNKNOWN.getDesc());
    }
}
