package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("edition")
public class Edition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本库ID
     */
    @ApiModelProperty("版本库ID不用传入")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 属于哪一个软件
     */
    @ApiModelProperty("传入查询到的软件名称")
    private String softwareId;

    @ApiModelProperty("发布者ID")
    private String userId;

    @ApiModelProperty("文章标题")
    private String title;



    @ApiModelProperty("文章摘要")
    private String remark;
    /**
     * 版本号
     */
    @ApiModelProperty("版本号")
    private String versionNumber;

    /**
     * 版本描述
     */
    @ApiModelProperty("版本描述")
    private String eddescribe;

    /**
     * 该版本文件所在路径
     */
    @ApiModelProperty("该版本文件所在地址")
    private String fileLink;

    /**
     * 下载次数
     */
    @ApiModelProperty("该版本下载次数")
    private Integer downloadNum;


    @ApiModelProperty("解决方法")
    private String solution;

    /**
     * 发布时间
     */
    @ApiModelProperty("版本发布时间，自动创建")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime time;


}
