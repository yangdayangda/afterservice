package com.example.afterservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Software;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.SoftwareService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("software")
@Api(tags = "软件名称相关接口")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @ApiOperation("得到全部的记录，传参为第几页，每页大小")
    @GetMapping()
    public RestResponse getAll(int pageIndex,int size){
        IPage<Software> list = softwareService.getAll(pageIndex,size);
        return new RestResponse(list);
    }

    @ApiOperation("根据名字增加软件")
    @OperLog(operModul = "软件类型处理",operType = "新增软件")
    @PostMapping()
    public RestResponse addSoftware(String name){
        softwareService.addSoftware(name);
        log.info("- 管理员增加了软件 {}",name);
        return new RestResponse();
    }

    @ApiOperation("删除软件，传入软件的名称")
    @OperLog(operModul = "软件类型处理",operType = "删除软件")
    @DeleteMapping("{name}")
    public RestResponse deleteSoftware(@PathVariable String name){
        softwareService.deleteSoftware(name);
        log.info("- 管理员删除了软件 {}",name);
        return new RestResponse();
    }


}
