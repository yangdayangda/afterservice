package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.entity.User;
import com.example.afterservice.utils.JWTUtil;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("feedBack")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @ApiOperation("提交反馈信息")
    @PostMapping("add")
    public RestResponse addFeedBack(HttpServletRequest request, Feedback feedback){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        feedback.setUserId(id);
        feedbackService.addFeedback(feedback);
        return new RestResponse();
    }

    @ApiOperation("根据ID删除该反馈信息")
    @GetMapping("delete")
    public RestResponse deleteFeedback(String id){
        feedbackService.deleteFeedback(id);
        return new RestResponse();
    }

    @ApiOperation("分页查询我的反馈信息，传入当前页数和大小")
    @GetMapping("getMyFeedback")
    public RestResponse getMyFeedback(HttpServletRequest request,int pageIndex,int size){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        List<Feedback> feedbacks = feedbackService.getMyFeedback(id,pageIndex,size);
        return new RestResponse(feedbacks);
    }

    @ApiOperation("得到我的反馈全部条数")
    @GetMapping("count")
    public RestResponse getCounts(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        int i = feedbackService.getCounts(id);
        return new RestResponse(i);
    }

    @ApiOperation("传入反馈进行更新")
    @PostMapping("update")
    public RestResponse updateFeedback(Feedback feedback){
        feedbackService.updateFeedback(feedback);
        return new RestResponse();
    }

    @ApiOperation("得到所有的反馈信息,分页查询")
    @PostMapping("getAll")
    public RestResponse getAllFeedback(Feedback feedback,int pageIndex,int size){
        List<Feedback> feedbacks = feedbackService.getAllFeedback(feedback,pageIndex,size);
        return new RestResponse(feedbacks);
    }

    @ApiOperation("传入查询的条件，得到全部记录的条数")
    @GetMapping("getAllCounts")
    public RestResponse getAllCounts(Feedback feedback){
        int i = feedbackService.getAllCounts(feedback);
        return new RestResponse(i);
    }

    @ApiOperation("根据传入的反馈ID，传出所有符合该反馈的技术人员")
    @GetMapping("getUserByid")
    public RestResponse getUserById(String feedBackId){
        List<User> users = feedbackService.getUserById(feedBackId);
        return new RestResponse(users);
    }
}
