package com.example.afterservice.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.Aop.OperLog;
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
import java.util.*;

@Api(tags = "角色资源相关接口")
@RestController
@CrossOrigin
@RequestMapping("role-premission")
public class RolePremissionController {

    @Autowired
    private RolePremissionService rolePremissionService;

    @Autowired
    private UserRoleService userRoleService;

    @ApiOperation("得到当前用户的全部权限")
    @GetMapping()
    public RestResponse getAllPre(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();

        Set<String> roles = userRoleService.getRoleById(id);

        Set<Premission> premissions = rolePremissionService.getAllPreByRoles(roles);

        int[] ints = new int[18];
        for (Premission p :
                premissions) {
            ints[p.getId()] = 1;
        }
        return new RestResponse(ints);
    }

    @ApiOperation("通过传入角色的名称得到角色有哪些权限，当前就三种角色：user，programmer，admin")
    @GetMapping("{name}")
    public RestResponse getAllPreByName(@PathVariable String name){
        HashSet<String> set = new HashSet<>();
        set.add(name);
        Set<Premission> premissions = rolePremissionService.getAllPreByRoles(set);
        int[] ints = new int[18];
        for (Premission p :
                premissions) {
            ints[p.getId()] = 1;
        }
        return new RestResponse(ints);
    }

    @ApiOperation("list里面传拥有的权限列表，role传当前更改的角色名称")
    @OperLog(operModul = "权限处理",operType = "更改权限")
    @PostMapping()
    public RestResponse updateRolePre(@RequestParam(value = "premission") List<String> premission,String role){
        ArrayList<String> list = new ArrayList<>();
        for (String i :
                premission) {
            if (!i.equals("#")){
                list.add(i);
            }
        }
        rolePremissionService.updatePremission(list,role);
        return new RestResponse();
    }


}
