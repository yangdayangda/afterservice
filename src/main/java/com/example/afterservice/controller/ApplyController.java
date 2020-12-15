package com.example.afterservice.controller;

import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.utils.JWTUtil;
import com.example.afterservice.vo.Apply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "申请相关接口")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("apply")
public class ApplyController {
    @Autowired
    private UserService userService;

    @ApiOperation("得到全部申请的用户信息包括申请的角色")
    @GetMapping()
    public RestResponse getAllApply(){
        List<User> users = userService.getApply();
        return new RestResponse(users);
    }


    @ApiOperation("添加申请")
    @OperLog(operModul = "申请处理",operType = "增加")
    @PostMapping()
    public RestResponse addApply(HttpServletRequest request, Apply apply){
        String token = request.getHeader("Authorization");
        String id = JWTUtil.getObjectByToken(token, "id");
        User user = UserConvert.INSTANCE.apply2user(apply);
        user.setId(id);
        user.setAuditStatus("1");
        userService.updateUser(user);
        log.info("- 用户{}发起了申请",user.getId());
        return new RestResponse();
    }

    @ApiOperation("处理申请的信息")
    @OperLog(operModul = "申请处理",operType = "处理申请")
    @PatchMapping()
    public RestResponse updateApply(Apply apply){
        User user = UserConvert.INSTANCE.apply2user(apply);
        userService.updateUser(user);
        log.warn("- 管理员处理了用户ID为{}的请求信息",apply.getId());
        return new RestResponse();
    }
}
