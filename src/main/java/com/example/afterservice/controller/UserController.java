package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询该用户在数据库是否存在，当传入参数为空是返回所有值")
    @PostMapping("/query")
    public RestResponse queryUser(User user){
        RestResponse restResponse = new RestResponse();
        restResponse.setResult(userService.queryUser(user));
        return restResponse;
    }

    @ApiOperation("发送验证码，得到的key值用于等会的验证")
    @GetMapping("/getCode")
    public RestResponse getEmailCode(String email){
        RestResponse restResponse = new RestResponse();
        String key = userService.sendEmailCode(email);
        restResponse.setResult(key);
        return restResponse;
    }

    @ApiOperation("注册用户，传入key值和验证码，")
    @PostMapping("/registerUser")
    public RestResponse registerUser(UserVo userVo){
        RestResponse restResponse = new RestResponse();
        userService.checkCode(userVo.getKey(),userVo.getCode());
        User user = UserConvert.INSTANCE.vo2entity(userVo);
        userService.addUser(user);
        return restResponse;
    }

    @ApiOperation("验证码登陆，先要发送验证码")
    @GetMapping("/loginByCode")
    private RestResponse loginByCode(String email,String key,String code){
        RestResponse restResponse = new RestResponse();
        userService.checkCode(key,code);
        User user = new User();
        user.setEmail(email);
        List<User> users = userService.queryUser(user);
        restResponse.setResult(users);
        return restResponse;
    }

    @ApiOperation("通过ID值来更改用户，所以传入的参数当中必须含有ID")
    @PostMapping("/update")
    public RestResponse updateUser(User user){
        userService.updateUser(user);
        return new RestResponse();
    }


}
