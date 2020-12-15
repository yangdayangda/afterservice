package com.example.afterservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
public interface FeedbackService  {

    void addFeedback(Feedback feedback);

    void deleteFeedback(String id);

    IPage<Feedback> getMyFeedback(String id, int pageIndex, int size);

    void updateFeedback(Feedback feedback);

    IPage<Feedback> getAllFeedback(Feedback feedback,int pageIndex,int size);

    List<User> getUserById(String feedBackId);
}
