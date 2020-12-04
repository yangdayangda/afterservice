package com.example.afterservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserVo {
    @ApiModelProperty("邮箱")
    @NotNull(message = "邮箱输入不能为空")
    @Email(message = "输入必须是邮箱格式")
    private String email;

    @ApiModelProperty("验证码")
    @Size(max = 6,min = 6,message = "传递的验证码登录验证码必须为6位")
    private String code;

    @ApiModelProperty("key值用于和code一起校验")
    private String key;

    @ApiModelProperty("密码")
    @NotNull(message = "输入的密码不能为空")
    private String password;
}
