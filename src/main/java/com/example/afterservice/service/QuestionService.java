package com.example.afterservice.service;

import com.example.afterservice.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
public interface QuestionService  {

    List<Question> getAllType();

    void addType(String name);
}
