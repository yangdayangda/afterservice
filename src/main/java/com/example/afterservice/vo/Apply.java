package com.example.afterservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Apply {

    @ApiModelProperty("用户ID")
    private String id;

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
