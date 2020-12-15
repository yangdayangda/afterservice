package com.example.afterservice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PageVo {

    @ApiModelProperty("当前页")
    private Long pageIndex;

    @ApiModelProperty("每页大小")
    private Long size;

    @ApiModelProperty("查询开始的时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime startTime=LocalDateTime.MIN;

    @ApiModelProperty("查询结束的时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private LocalDateTime endTime=LocalDateTime.now();
}
