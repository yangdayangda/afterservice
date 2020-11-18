package com.example.afterservice.mapper;

import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    void testselect(){
        System.out.println(userMapper.selectList(null));
    }

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
}
