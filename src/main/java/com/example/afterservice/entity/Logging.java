package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "logging")
public class Logging {

    @ApiModelProperty("日志的ID")
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty("日志信息")
    private String message ;

    @ApiModelProperty("日志等级")
    private String levelString;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime createdTime;

    @ApiModelProperty("日志的全类名")
    private String loggerName;
}
