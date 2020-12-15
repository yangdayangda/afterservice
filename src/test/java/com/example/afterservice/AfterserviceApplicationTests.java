package com.example.afterservice;

import com.example.afterservice.vo.PageVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

class AfterserviceApplicationTests {


    @Test
    void contextLoads() {
        PageVo pageVo = new PageVo();
        System.out.println(pageVo.getStartTime());
        System.out.println(pageVo.getEndTime());
    }


}
