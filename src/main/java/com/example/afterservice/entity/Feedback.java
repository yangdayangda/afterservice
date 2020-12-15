package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈ID
     */
    @ApiModelProperty("反馈信息的ID，自动生成，不用传入")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 反馈者的ID
     */
    @ApiModelProperty("反馈者的id,header当中有，不用传入")
    private String userId;

    /**
     * 对哪一个软件反馈
     */
    @ApiModelProperty("对哪一个软件进行反馈，下拉列表从数据库获取值")
    private String softwareName;

    /**
     * 反馈的类型
     */
    @ApiModelProperty("出现问题的类型，下拉列表从数据库获取值")
    private String questionType;

    /**
     * 反馈的精简描述
     */
    @ApiModelProperty("反馈的精简描述")
    private String briefDescribe;

    /**
     * 详细描述
     */
    @ApiModelProperty("详细描述，可为空")
    private String declareDescribe;

    /**
     * 上传文件的路径
     */
    @ApiModelProperty("上传文件的路径，可为空")
    private String uploadFile;

    /**
     * 1表示未处理，2表示正在处理，3表示处理完毕
     */
    @ApiModelProperty("1表示未处理，2表示正在处理，3表示处理完毕，数据库默认1")
    @TableField("isdeal")
    private Integer isdeal;

    /**
     * 反馈发出时间
     */
    @ApiModelProperty("反馈时间，数据库自动生成")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime time;

    /**
     * 处理人员的ID
     */
    @ApiModelProperty("处理人员的ID")
    private String dealUserId;

    /**
     * 处理方法
     */
    @ApiModelProperty("处理的方法")
    private String solution;

    @ApiModelProperty("手机号码，联系时使用，可不传")
    private String phone;

    @ApiModelProperty("邮箱，联系时使用，可不传")
    private String email;
}
