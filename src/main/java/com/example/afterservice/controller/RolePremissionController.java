package com.example.afterservice.controller;

import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.service.RolePremissionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("rolePremission")
public class RolePremissionController {

    @Autowired
    private RolePremissionService rolePremissionService;

    @ApiOperation("list里面传拥有的权限列表，role传当前更改的角色名称")
    @PostMapping("update")
    public RestResponse updateRolePre(@RequestParam(required = false, value = "list[]") List<String> premission,String role){
        rolePremissionService.updatePremission(premission,role);
        return new RestResponse(premission);
    }
}
