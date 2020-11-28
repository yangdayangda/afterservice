package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Software;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.SoftwareService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/software")
@Api(value = "", tags = "", description="")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @ApiOperation("得到所有软件的数量")
    @GetMapping("/count")
    public RestResponse getCount(){
        int i = softwareService.getCount();
        return new RestResponse(i);
    }

    @ApiOperation("得到全部的记录，传参为第几页，每页大小")
    @GetMapping("/all")
    public RestResponse getAll(int pageIndex,int size){
        List<Software> list = softwareService.getAll(pageIndex,size);
        return new RestResponse(list);
    }

    @ApiOperation("根据名字增加软件")
    @GetMapping("/add")
    public RestResponse addSoftware(String name){
        softwareService.addSoftware(name);
        return new RestResponse();
    }

    @ApiOperation("删除软件，传入软件的名称")
    @GetMapping("/delete")
    public RestResponse deleteSoftware(String name){
        softwareService.deleteSoftware(name);
        return new RestResponse();
    }


}
