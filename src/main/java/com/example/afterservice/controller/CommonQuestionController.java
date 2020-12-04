package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.CommonQuestion;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.CommonQuestionService;
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
@RequestMapping("commonQuestion")
@Api(tags = "常见问题处理接口")
public class CommonQuestionController {

    @Autowired
    private CommonQuestionService commonQuestionService;

    @ApiOperation("得到全部的常见问题，传入第几页，每页大小")
    @GetMapping("getAll")
    public RestResponse getAll(Integer pageIndex,Integer size){
        List<CommonQuestion> commonQuestions = commonQuestionService.getAll(pageIndex,size);
        return new RestResponse(commonQuestions);
    }

    @ApiOperation("增加常见问题")
    @PostMapping("add")
    public RestResponse addComQuestion(CommonQuestion commonQuestion){
        commonQuestionService.addComQuestion(commonQuestion);
        return new RestResponse();
    }

    @ApiOperation("更新常见问题")
    @PostMapping("update")
    public RestResponse updateComQuestion(CommonQuestion commonQuestion){
        commonQuestionService.updateComQuestion(commonQuestion);
        return new RestResponse();
    }
}
