package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Question;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.QuestionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("question")
@Api(value = "", tags = "", description="")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("all")
    public RestResponse getAll(){
        List<Question>  questions =  questionService.getAllType();
        return new RestResponse(questions);
    }

    @GetMapping("add")
    public RestResponse addType(String name){
        questionService.addType(name);
        return new RestResponse();
    }
}
