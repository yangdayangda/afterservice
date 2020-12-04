package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Logging;
import com.example.afterservice.service.LoggingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("logging")
@Api( tags = "日志管理相关接口")
public class LoggingController {

    @Autowired
    private LoggingService loggingService;

    @ApiOperation("分页获取所有的日志")
    @GetMapping("getAll")
    public RestResponse getAll(Integer pageIndex, Integer size){
        List<Logging> loggings = loggingService.getAll(pageIndex,size);
        return new RestResponse(loggings);
    }

    @ApiOperation("得到所有日志的总数量")
    @GetMapping("getAllCounts")
    public RestResponse getAllCounts(){
        int i = loggingService.getAllCounts();
        return new RestResponse(i);
    }


}
