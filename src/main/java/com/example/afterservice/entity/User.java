package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @ApiModelProperty("用户名")
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
     * 注册时间
     */
    @ApiModelProperty("注册时间，数据库创建不用增加")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime registerTime;

    @ApiModelProperty("申请的角色的ID")
    private String roleId;

    @ApiModelProperty("申请审核状态，0未申请，1已申请待审核，2通过审核，3未通过审核")
    private String auditStatus;

    @ApiModelProperty("该软件公司申请职员的工作牌照片")
    private String staffImg;

    @ApiModelProperty("身份证正面照")
    private String idCardFrontImg;

    @ApiModelProperty("身份证反面照")
    private String idCardBackImg;
}
