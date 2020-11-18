package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.UUID)
    @ApiModelProperty("用户ID")
    private String id;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户ID")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 用户头像地址
     */
    @ApiModelProperty("用户头像路径")
    private String img;

    /**
     * 性别男女
     */
    @ApiModelProperty("性别男女")
    private String sex;

    /**
     * 电话号码
     */
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 邮件
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 用户组
     */
    @ApiModelProperty("用户组")
    private String duty;

    /**
     * 注册时间
     */
    @ApiModelProperty("注册时间，数据库创建不用增加")
    private LocalDateTime registerTime;


}
