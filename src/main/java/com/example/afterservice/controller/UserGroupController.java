package com.example.afterservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.UserGroup;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.UserGroupService;
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
@RequestMapping("/userGroup")
@Api(value = "", tags = "", description="")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @ApiOperation("分页查询得到所有的用户组，传入当前页数，每页大小")
    @GetMapping("/all")
    public RestResponse getAllUgroup(int pageIndex, int size){
        List<UserGroup> lists = userGroupService.getAllUgroup(pageIndex,size);
        return new RestResponse(lists);
    }

    @ApiOperation("获取全部记录的数量")
    @GetMapping("/szie")
    public RestResponse getUgroupSize(){
        int size = userGroupService.getUgroupSize();
        return new RestResponse(size);
    }

    @ApiOperation("增加用户组")
    @GetMapping("/add")
    public RestResponse addUgroup(String name){
        userGroupService.addUgroup(name);
        return new RestResponse();
    }

    @ApiOperation("删除用户组")
    @GetMapping("/delete")
    public RestResponse deleteUgroup(String name){
        userGroupService.deleteUgroup(name);
        return new RestResponse();
    }
}
