package com.example.afterservice.service;

import com.example.afterservice.entity.Edition;
import com.example.afterservice.entity.User;
import com.example.afterservice.entity.UserDto;

import java.util.List;

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

    List<UserDto> getAllUser();

    List<User> getApply();

    List<User> vagueSelect(String username);

    void deleteById(String id);
}
