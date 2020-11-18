package com.example.afterservice.common.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestResponse {
    private String Code = "200";
    private String Message = "正常";
    private Object result;
    public RestResponse(String errCode, String errMessage){
        this.Code = errCode;
        this.Message= errMessage;
    }
    public RestResponse(Object result){
        this.result = result;
    }
}