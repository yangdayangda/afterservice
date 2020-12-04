package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.Question;
import com.example.afterservice.mapper.QuestionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.service.QuestionService;
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
public class QuestionServiceImpl  implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public List<Question> getAllType() {
        return questionMapper.selectList(null);
    }

    @Override
    public void addType(String name) {
        Question question = new Question();
        question.setType(name);
        int insert = questionMapper.insert(question);
        if (insert==0){
            throw new BusinessException(CommonErrorCode.E_100118);
        }
    }

    @Override
    public void deleteByName(String name) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.eq("type",name);
        int i = questionMapper.delete(wrapper);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100119);
        }

    }
}
