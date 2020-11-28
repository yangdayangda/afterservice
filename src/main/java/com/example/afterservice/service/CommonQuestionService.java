package com.example.afterservice.service;

import com.example.afterservice.entity.CommonQuestion;
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
public interface CommonQuestionService {

    List<CommonQuestion> getAll(int pageIndex, int size);

    void addComQuestion(CommonQuestion commonQuestion);

    void updateComQuestion(CommonQuestion commonQuestion);
}
