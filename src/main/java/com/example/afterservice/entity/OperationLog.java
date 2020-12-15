package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@TableName(value = "operlog")
public class OperationLog {

    @ApiModelProperty("操作ID")
    @TableId(value = "oper_id",type = IdType.UUID)
    private String operId;

    @ApiModelProperty("操作模块")
    private String operModul;

    @ApiModelProperty("操作类型")
    private String operType;

    @ApiModelProperty("操作描述")
    private String operDesc;

    @ApiModelProperty("操作请求参数")
    private String operRequParam;

    @ApiModelProperty("操作返回参数")
    private String operRespParam;

    @ApiModelProperty("操作人员的ID")
    private String operUserId;

    @ApiModelProperty("操作人员的名字")
    private String operUserName;

    @ApiModelProperty("操作的方法")
    private String operMethod;

    @ApiModelProperty("操作请求的地址")
    private String operUri;

    @ApiModelProperty("操作的ip地址")
    private String operIp;

    @ApiModelProperty("操作时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime operCreateTime;

    @ApiModelProperty("操作版本")
    private String operVer;
}
