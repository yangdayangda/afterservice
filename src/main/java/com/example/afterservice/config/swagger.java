package com.example.afterservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration

@EnableSwagger2

public class swagger {

    @Bean

    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)

                .apiInfo(apiInfo())

//                是否启用swagger，默认为true，可以不配

                .enable(true)

                .select()

                .apis(RequestHandlerSelectors.basePackage("com.example.afterservice.controller"))

//                过滤器，只扫描该请求路径下的接口，any，表示全部扫描

                .paths(PathSelectors.any())

                .build();

    }

//配置swaggerapi信息

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("杨达", "http://xxx.xxx.com/联系人访问链接", "2852635969@qq.com");
        return new ApiInfo(
                "软件售后服务接口", // 标题
                "软解售后服务的详细接口信息，用于测试使用", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }



}