package com.example.afterservice.controller;


import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Question;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.QuestionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(tags = "问题类型相关接口")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @ApiOperation("得到全部问题的类型")
    @GetMapping()
    public RestResponse getAll(){
        List<Question>  questions =  questionService.getAllType();
        return new RestResponse(questions);
    }

    @ApiOperation("增加问题类型")
    @OperLog(operModul = "问题类型处理",operType = "新增问题")
    @PostMapping()
    public RestResponse addType(String name){
        questionService.addType(name);
        log.info("管理员增加了问题类型 {}",name);
        return new RestResponse();
    }

    @ApiOperation("传入问题类型进行删除")
    @OperLog(operModul = "问题类型处理",operType = "删除问题")
    @DeleteMapping("{name}")
    public RestResponse deleteQuestion(@PathVariable String name){
        questionService.deleteByName(name);
        log.info("删除了问题类型 {}",name);
        return new RestResponse();
    }
}
