package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.ErrorCode;
import com.example.afterservice.entity.User;
import com.example.afterservice.entity.UserDto;
import com.example.afterservice.entity.UserRole;
import com.example.afterservice.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.mapper.UserRoleMapper;
import com.example.afterservice.service.UserService;
import com.example.afterservice.shiro.token.CustomizedToken;
import com.example.afterservice.utils.JWTUtil;
import com.example.afterservice.utils.MailUtil;
import com.example.afterservice.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MailUtil mailUtil;
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
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        long time = 600;
        String key = UUID.randomUUID().toString();
        redisUtil.set(key,verifyCode,time);
        try{
            mailUtil.sendEmail(email,verifyCode);
        }catch (Exception e){
            throw new BusinessException(CommonErrorCode.E_100107);
        }
        return key;
    }

    @Override
    public void checkCode(String key, String code) {
        if (StringUtils.isBlank(code) || StringUtils.isBlank(key)){
            throw new BusinessException(CommonErrorCode.E_100103);
        }
        String s = (String) redisUtil.get(key);
        if(!code.equals(s)){
            throw new BusinessException(CommonErrorCode.E_100102);
        }
    }

    @Override
    public void addUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setId(id);
        Md5Hash password = new Md5Hash(user.getPassword(), id, 1024);
        user.setPassword(password.toHex());
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email",user.getEmail());
        Integer integer = userMapper.selectCount(wrapper);
        if (integer!=0){
            throw new BusinessException(CommonErrorCode.E_100113);
        }
        int i = userMapper.insert(user);
        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRoleMapper.insert(userRole);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100116);
        }
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }


    @Override
    public String loginByPassword(String email, String password) {
        CustomizedToken customizedToken = new CustomizedToken(email, password, "Password");
        Subject subject = SecurityUtils.getSubject();
        subject.login(customizedToken);
        User user = new User();
        user.setEmail(email);
        List<User> users = queryUser(user);
        String id = users.get(0).getId();
        String name = users.get(0).getUsername();
        HashMap<String, String> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        return JWTUtil.getToken(map);
    }

    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<UserDto> getAllUser() {

        return userMapper.getAllUser();
    }

    @Override
    public List<User> getApply() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne("audit_status","0");
        List<User> users = userMapper.selectList(wrapper);
        return users;
    }

    @Override
    public List<User> vagueSelect(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username",username);
        return userMapper.selectList(wrapper);
    }

    @Override
    public void deleteById(String id) {
        int i = userMapper.deleteById(id);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100119);
        }
    }

}
