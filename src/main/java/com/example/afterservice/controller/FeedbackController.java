package com.example.afterservice.controller;


import org.springframework.stereotype.Controller;
import com.example.afterservice.service.FeedbackService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

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
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
}
