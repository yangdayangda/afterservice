package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.utils.JWTUtil;
import com.example.afterservice.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @ApiOperation("查询单一用户，返回该用户的token")
    @PostMapping("/query")
    public RestResponse queryUser(User user){
        RestResponse restResponse = new RestResponse();
        List<User> users = userService.queryUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("id",users.get(0).getId());
        String token = JWTUtil.getToken(map);
        restResponse.setResult(token);

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

    @ApiOperation("验证码登陆，先要发送验证码，返回该登陆用户的token")
    @GetMapping("/loginByCode")
    private RestResponse loginByCode(String email,String key,String code){
        RestResponse restResponse = new RestResponse();
        userService.checkCode(key,code);
        User user = new User();
        user.setEmail(email);
        List<User> users = userService.queryUser(user);

        HashMap<String, String> map = new HashMap<>();
        map.put("id",users.get(0).getId());
        String token = JWTUtil.getToken(map);
        restResponse.setResult(token);

        return restResponse;
    }

    @ApiOperation("header请求头当中存token，其他传要改变的值")
    @PostMapping("/update")
    public RestResponse updateUser(HttpServletRequest request,User user){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        user.setId(id);

        userService.updateUser(user);
        return new RestResponse();
    }


}
