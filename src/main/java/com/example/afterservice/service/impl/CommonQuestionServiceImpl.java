package com.example.afterservice.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.CommonQuestion;
import com.example.afterservice.mapper.CommonQuestionMapper;
import com.example.afterservice.service.CommonQuestionService;
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
public class CommonQuestionServiceImpl  implements CommonQuestionService {

    @Autowired
    private CommonQuestionMapper commonQuestionMapper;


    @Override
    public List<CommonQuestion> getAll(int pageIndex, int size) {
        Page<CommonQuestion> page = new Page<>(pageIndex, size);
        IPage<CommonQuestion> iPage = commonQuestionMapper.selectPage(page, null);
        return iPage.getRecords();
    }

    @Override
    public void addComQuestion(CommonQuestion commonQuestion) {
        int i = commonQuestionMapper.insert(commonQuestion);
        if (i ==0 ){
            throw new BusinessException(CommonErrorCode.E_100118);
        }
    }

    @Override
    public void updateComQuestion(CommonQuestion commonQuestion) {
        int i = commonQuestionMapper.updateById(commonQuestion);
        if(i == 0){
            throw new BusinessException(CommonErrorCode.E_100120);
        }
    }
}
