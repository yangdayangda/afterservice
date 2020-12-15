package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.Feedback;
import com.example.afterservice.entity.User;
import com.example.afterservice.mapper.FeedbackMapper;
import com.example.afterservice.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Slf4j
@Service
@Transactional
public class FeedbackServiceImpl  implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public void addFeedback(Feedback feedback) {
        int i = feedbackMapper.insert(feedback);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100118);
        }
    }

    @Override
    public void deleteFeedback(String id) {
        int i = feedbackMapper.deleteById(id);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100119);
        }
    }

    @Override
    public IPage<Feedback> getMyFeedback(String id,int pageIndex, int size) {
        Page<Feedback> page = new Page<>(pageIndex,size);
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        IPage<Feedback> feedbackIPage = feedbackMapper.selectPage(page, wrapper);

        return feedbackIPage;
    }

    @Override
    public void updateFeedback(Feedback feedback) {
        int i = feedbackMapper.updateById(feedback);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100119);
        }
    }

    @Override
    public IPage<Feedback> getAllFeedback(Feedback feedback,int pageIndex,int size) {
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>(feedback);
        wrapper.orderByDesc("time");
        Page<Feedback> page = new Page<Feedback>(pageIndex, size);
        IPage<Feedback> feedbackIPage = feedbackMapper.selectPage(page, wrapper);
        return feedbackIPage;
    }


    @Override
    public List<User> getUserById(String feedBackId) {

        return feedbackMapper.getUserById(feedBackId);
    }
}
