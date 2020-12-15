package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.entity.User;
import com.example.afterservice.utils.JWTUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.FeedbackService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "反馈相关接口", description="")
@RequestMapping("feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation("分页查询我的反馈信息，传入当前页数和大小")
    @GetMapping()
    public RestResponse getMyFeedback(HttpServletRequest request,int pageIndex,int size){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        IPage<Feedback> feedbacks = feedbackService.getMyFeedback(id,pageIndex,size);
        return new RestResponse(feedbacks);
    }

    @ApiOperation("根据传入的反馈ID，传出所有符合该反馈的技术人员")
    @RequiresPermissions("feedback:update")
    @GetMapping("{feedBackId}")
    public RestResponse getUserById(@PathVariable("feedBackId") String feedBackId){
        List<User> users = feedbackService.getUserById(feedBackId);
        return new RestResponse(users);
    }

    @ApiOperation("得到所有的反馈信息,分页查询")
    @RequiresPermissions("feedback:update")
    @GetMapping("get-all")
    public RestResponse getAllFeedback(Feedback feedback,int pageIndex,int size){
        IPage<Feedback> feedbacks = feedbackService.getAllFeedback(feedback,pageIndex,size);
        return new RestResponse(feedbacks);
    }

    @ApiOperation("提交反馈信息")
    @OperLog(operModul = "反馈处理",operType = "新增反馈")
    @RequiresPermissions("feedback:add")
    @PostMapping()
    public RestResponse addFeedBack(HttpServletRequest request, Feedback feedback){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        feedback.setUserId(id);
        feedbackService.addFeedback(feedback);
        log.info("- 用户{}提交了反馈信息",id);
        return new RestResponse();
    }

    @ApiOperation("传入反馈进行更新")
    @RequiresPermissions("feedback:update")
    @PatchMapping()
    public RestResponse updateFeedback(Feedback feedback){
        feedbackService.updateFeedback(feedback);
        log.info("- 反馈{}信息被更新",feedback.getId());
        return new RestResponse();
    }


    @ApiOperation("根据ID删除该反馈信息")
    @OperLog(operModul = "反馈信息处理",operType = "删除反馈")
    @RequiresPermissions("feedback:delete")
    @DeleteMapping(value = "{id}")
    public RestResponse deleteFeedback(@PathVariable("id") String id){
        feedbackService.deleteFeedback(id);
        log.info("- 反馈{}被删除",id);
        return new RestResponse();
    }
}
