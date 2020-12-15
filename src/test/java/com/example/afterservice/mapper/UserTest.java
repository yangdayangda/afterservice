package com.example.afterservice.mapper;

import com.alibaba.druid.filter.config.ConfigTools;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.convert.UserConvert;
import com.example.afterservice.entity.OperationLog;
import com.example.afterservice.entity.User;
import com.example.afterservice.service.UserService;
import com.example.afterservice.utils.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OperLogMapper operLogMapper;

    /**
     * 测试邮箱验证码是否正常
     */
    @Test
    void testCode(){
        userService.checkCode("sms:d36c5c213113482ebefd68c5d808f4e9","709874");
    }

    /**
     * 测试数据库连接查询是否正常
     */
    @Test
    void testSelect(){
        User user = new User();
        user.setEmail("2842635969@qq.com");
        System.out.println(userService.queryUser(user));
    }

    /**
     * 测试redis是否能放数据
     */
    @Test
    void testRedis(){
        User user = new User();
        user.setUsername("zhangshan");
        user.setPassword("12345");
        redisUtil.set("zhanshan",user);
        System.out.println(redisUtil.get("zhanshan"));
    }

    /**
     * 利用druid加密数据库密码
     * @throws Exception
     */
    @Test
    public void druidEncrypt() throws Exception {
        //密码明文
        String password = "123456";
        System.out.println("明文密码: " + password);
        String[] keyPair = ConfigTools.genKeyPair(512);
        //私钥
        String privateKey = keyPair[0];
        //公钥
        String publicKey = keyPair[1];

        //用私钥加密后的密文
        password = ConfigTools.encrypt(privateKey, password);

        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

        System.out.println("password:" + password);

        String decryptPassword = ConfigTools.decrypt(publicKey, password);
        System.out.println("解密后:" + decryptPassword);
    }

    @Test
    public void testPage(){
        Page<User> userPage = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(userPage, null);
        System.out.println(userIPage.descs());
        System.out.println(userIPage.getRecords());
        System.out.println(userIPage.condition());
        System.out.println(userIPage.getTotal());
        System.out.println(userIPage.toString());
    }

    @Test
    public void testOperLogPage(){
        Page<OperationLog> page = new Page<>(1, 9);
        IPage<OperationLog> iPage = operLogMapper.selectPage(page, null);
        System.out.println(iPage.descs());
        System.out.println(iPage.getRecords());
        System.out.println(iPage.condition());
        System.out.println(iPage.getTotal());
        System.out.println(iPage.toString());
    }
}
