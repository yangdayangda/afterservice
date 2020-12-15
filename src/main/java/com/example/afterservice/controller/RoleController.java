package com.example.afterservice.controller;

import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Role;
import com.example.afterservice.mapper.RoleMapper;
import com.example.afterservice.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = "角色增加相关接口")
@RestController
@CrossOrigin
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("得到全部的角色信息，remark表示备注是什么软件的")
    @GetMapping()
    public RestResponse getAllRole(){
        List<Role> roles = roleService.getAllRole();
        return new RestResponse(roles);
    }

    @ApiOperation("添加角色，默认权限是用户的权限")
    @OperLog(operModul = "角色处理",operType = "新增角色")
    @PostMapping()
    public RestResponse addRole(Role role){
        roleService.addRole(role);
        return new RestResponse();
    }

    @ApiOperation("删除角色，传入角色的名称")
    @OperLog(operModul = "角色处理",operType = "删除角色")
    @DeleteMapping("{name}")
    public RestResponse deleteRole(String name){
        roleService.deleteRole(name);
        return new RestResponse();
    }
}
