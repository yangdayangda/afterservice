package com.example.afterservice.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.service.UserRoleService;
import com.example.afterservice.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

@Api(tags = "用户角色相关接口")
@RestController
@CrossOrigin
@RequestMapping("user-role")
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("获取当前用户的角色")
    @GetMapping()
    public RestResponse getUserRole(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        Set<String> roles = userRoleService.getRoleById(id);
        return new RestResponse(roles);
    }

    @ApiOperation("根据用户ID获取用户的角色")
    @GetMapping("{id}")
    public RestResponse getUserRoleById(@PathVariable String id){
        Set<String> roles = userRoleService.getRoleById(id);
        return new RestResponse(roles);
    }


}
