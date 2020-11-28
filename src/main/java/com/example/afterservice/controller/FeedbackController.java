package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.utils.JWTUtil;
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
@Api(value = "", tags = "", description="")
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
}
