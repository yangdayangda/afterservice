package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.ErrorCode;
import com.example.afterservice.entity.User;
import com.example.afterservice.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<User> queryUser(User user) {
        List<User> userList = userMapper.selectList(new QueryWrapper<User>(user));
        if (userList.isEmpty()){
            throw new BusinessException(CommonErrorCode.E_100104);
        }
        return userList;
    }

    @Override
    public String sendEmailCode(String email) {
        //向验证码服务发送请求的地址
        String sms_url = "http://121.36.57.122:56085/sailing/generate?effectiveTime=300&name=sms";

        //请求体
        Map<String,Object> body = new HashMap<>();
        body.put("mobile",email);
        //请求头
        HttpHeaders httpHeaders =new HttpHeaders();
        //指定Content-Type: application/json
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        //请求信息,传入body，header
        HttpEntity httpEntity = new HttpEntity(body,httpHeaders);
        //向url请求
        ResponseEntity<Map> exchange = null;

        Map bodyMap = null;
        try {
            exchange = restTemplate.exchange(sms_url, HttpMethod.POST, httpEntity, Map.class);
            log.info("请求验证码服务，得到响应:{}",exchange);
            bodyMap = exchange.getBody();
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new RuntimeException("发送验证码失败");
        }
        if(bodyMap == null || bodyMap.get("result") == null){
            throw new RuntimeException("发送验证码失败");
        }

        Map result = (Map) bodyMap.get("result");
        String key = (String) result.get("key");
        log.info("得到发送验证码对应的key:{}",key);
        return key;
    }

    @Override
    public void checkCode(String key, String code) {
        //校验验证码的url
        String url = "http://121.36.57.122:56085/sailing/verify?name=sms&verificationCode="+code+"&verificationKey="+key;

        Map bodyMap = null;
        try {
            //使用restTemplate请求验证码服务
            ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, HttpEntity.EMPTY, Map.class);
            log.info("请求验证码服务，得到响应:{}",exchange);
            bodyMap = exchange.getBody();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(CommonErrorCode.E_100102);
//            throw new RuntimeException("校验验证码失败");

        }
        if(bodyMap == null || bodyMap.get("result") == null || !(Boolean) bodyMap.get("result")){
            throw new BusinessException(CommonErrorCode.E_100102);
        }
    }

    @Override
    public void addUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        Integer integer = userMapper.selectCount(wrapper);
        if (integer!=0){
            throw new BusinessException(CommonErrorCode.E_100113);
        }
        int i = userMapper.insert(user);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100116);
        }
    }

    @Override
    public void updateUser(User user) {

        userMapper.updateById(user);
    }
}
