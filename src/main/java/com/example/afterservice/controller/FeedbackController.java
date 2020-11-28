package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.utils.JWTUtil;
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

    @PostMapping("add")
    public RestResponse addFeedBack(HttpServletRequest request, Feedback feedback){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        feedback.setUserId(id);
        feedbackService.addFeedback(feedback);
        return new RestResponse();
    }

    @GetMapping("delete")
    public RestResponse deleteFeedback(String id){
        feedbackService.deleteFeedback(id);
        return new RestResponse();
    }

    @GetMapping("getMyFeedback")
    public RestResponse getMyFeedback(HttpServletRequest request,int pageIndex,int size){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        List<Feedback> feedbacks = feedbackService.getMyFeedback(id,pageIndex,size);
        return new RestResponse(feedbacks);
    }
}
