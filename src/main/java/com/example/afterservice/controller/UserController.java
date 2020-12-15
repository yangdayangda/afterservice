package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.entity.UserDto;
import com.example.afterservice.service.UserService;
import com.example.afterservice.utils.CommonUtils;
import com.example.afterservice.utils.JWTUtil;
import com.example.afterservice.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Api(tags = "用户处理相关接口")
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("得到全部用户的全部信息，需要当前角色拥有管理员权限")
    @GetMapping()
    public RestResponse getAllUser(){
        List<UserDto> allUser = userService.getAllUser();
        return new RestResponse(allUser);
    }

    @ApiOperation("得到当前用户的所有信息")
    @GetMapping("own")
    public RestResponse getInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        User user = userService.getUserById(id);
        return new RestResponse(user);
    }

    @ApiOperation("根据用户名精准查找用户")
    @GetMapping("accurate-select/{username}")
    public RestResponse accurateSelect(@PathVariable String username){
        User user = new User();
        user.setUsername(username);
        List<User> restUser = userService.queryUser(user);
        return new RestResponse(restUser.get(0));
    }

    @ApiOperation("根据用户名模糊查找")
    @GetMapping("vague-select/{username}")
    public RestResponse vagueSelect(@PathVariable String username){
        List<User> users = userService.vagueSelect(username);
        return new RestResponse(users);
    }

    @ApiOperation("发送验证码，得到的key值用于等会的验证")
    @GetMapping("send-email/{email}")
    public RestResponse getEmailCode(@PathVariable String email){
        RestResponse restResponse = new RestResponse();
        String key = userService.sendEmailCode(email);
        restResponse.setResult(key);
        return restResponse;
    }

    @ApiOperation("通过邮箱密码登陆，登录成功返回token")
    @OperLog(operModul = "用户处理" ,operType = "密码登陆",operDesc = "密码登陆该系统")
    @GetMapping("/login-by-password")
    public RestResponse loginByPassword(String email,String password){
        RestResponse restResponse = new RestResponse();
        String token = userService.loginByPassword(email,password);
        restResponse.setResult(token);
        return restResponse;
    }

    @ApiOperation("验证码登陆，先要发送验证码，返回该登陆用户的token")
    @OperLog(operModul = "用户处理",operType = "验证码登陆")
    @GetMapping("/login-by-code")
    public RestResponse loginByCode(String email,String key,String code){
        RestResponse restResponse = new RestResponse();
        userService.checkCode(key,code);
        User user = new User();
        user.setEmail(email);
        List<User> users = userService.queryUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("id",users.get(0).getId());
        map.put("name",users.get(0).getUsername());
        String token = JWTUtil.getToken(map);
        restResponse.setResult(token);
        return restResponse;
    }



    @ApiOperation("注册用户，传入key值和验证码，")
    @OperLog(operModul = "用户处理",operType = "注册")
    @PostMapping()
    public RestResponse registerUser(UserVo userVo){
        RestResponse restResponse = new RestResponse();
        userService.checkCode(userVo.getKey(),userVo.getCode());
        User user = UserConvert.INSTANCE.vo2entity(userVo);
        userService.addUser(user);
        return restResponse;
    }

    @ApiOperation("增加用户，什么要增加自己决定")
    @OperLog(operModul = "用户处理",operType = "新增用户")
    @RequiresPermissions("user:add")
    @PostMapping("add-user")
    public RestResponse addUser(User user){
        userService.addUser(user);
        return new RestResponse();
    }

    @ApiOperation("更改任意用户用户信息，需要相应权限")
    @RequiresPermissions("user:update")
    @PatchMapping()
    public RestResponse updateUser(User user){
        userService.updateUser(user);
        return new RestResponse();
    }

    @ApiOperation("更改自己用户信息")
    @OperLog(operModul = "用户处理",operType = "更新用户信息")
    @PutMapping()
    public RestResponse updateOwn(HttpServletRequest request,User user){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        user.setId(id);
        userService.updateUser(user);
        return new RestResponse();
    }


    @ApiOperation("根据用户ID删除用户")
    @OperLog(operModul = "用户处理",operType = "删除用户")
    @DeleteMapping("{id}")
    public RestResponse delete(@PathVariable String id){
        userService.deleteById(id);
        return new RestResponse();
    }
}
