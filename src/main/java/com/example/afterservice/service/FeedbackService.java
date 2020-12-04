package com.example.afterservice.service;

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

    List<Feedback> getMyFeedback(String id,int pageIndex, int size);

    int getCounts(String id);

    void updateFeedback(Feedback feedback);

    List<Feedback> getAllFeedback(Feedback feedback,int pageIndex,int size);

    int getAllCounts(Feedback feedback);

    List<User> getUserById(String feedBackId);
}
