package com.example.afterservice.mapper;

import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;


//    @Test
//    void testInsert(){
//        UserVo user = new UserVo();
//        user.setPhone("435353535");
//        user.setEmail("tgertet");
//        user.setPassword("43242424");
//        User user1 = UserConvert.INSTANCE.vo2entity(user);
//        System.out.println(user1.getPhone());
//        System.out.println(userMapper.insert(user1));
//    }

//    @Test
//    void testCode(){
//        userService.checkCode("sms:d36c5c213113482ebefd68c5d808f4e9","709874");
//    }
//
//    @Test
//    void testSelect(){
//        User user = new User();
//        user.setEmail("2842635969@qq.com");
//        System.out.println(userService.queryUser(user));
//    }
}
