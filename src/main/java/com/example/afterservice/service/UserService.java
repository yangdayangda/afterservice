package com.example.afterservice.service;

import com.example.afterservice.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
public interface UserService {

    List<User> queryUser(User user);

    String sendEmailCode(String email);

    void checkCode(String key, String code);

    void addUser(User user);

    void updateUser(User user);


    String loginByPassword(String phone, String password);

    User getUserById(String id);

}
