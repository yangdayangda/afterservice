package com.example.afterservice.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Premission;
import com.example.afterservice.service.RolePremissionService;
import com.example.afterservice.service.UserRoleService;
import com.example.afterservice.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(tags = "角色资源相关接口")
@RestController
@CrossOrigin
@RequestMapping("rolePremission")
public class RolePremissionController {

    @Autowired
    private RolePremissionService rolePremissionService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("list里面传拥有的权限列表，role传当前更改的角色名称")
    @PostMapping("update")
    public RestResponse updateRolePre(@RequestParam(required = false, value = "list[]") List<String> premission,String role){
        rolePremissionService.updatePremission(premission,role);
        return new RestResponse(premission);
    }

    @ApiOperation("得到当前用户的全部权限")
    @GetMapping("getAllPre")
    public RestResponse getAllPre(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();

        Set<String> roles = userRoleService.getRoleById(id);

        Set<Premission> premissions = rolePremissionService.getAllPreByRoles(roles);

        int[] ints = new int[14];
        for (Premission p :
                premissions) {
            ints[p.getId()] = 1;
        }
        return new RestResponse(ints);
    }
}
