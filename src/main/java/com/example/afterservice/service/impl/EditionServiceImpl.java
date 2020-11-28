package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.Edition;
import com.example.afterservice.mapper.EditionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.service.EditionService;
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
public class EditionServiceImpl  implements EditionService {

    @Autowired
    private EditionMapper editionMapper;


    @Override
    public void addEdition(Edition edition) {
        int i = editionMapper.insert(edition);
        if (i == 0){
            throw new BusinessException(CommonErrorCode.E_100118);
        }
    }

    @Override
    public List<Edition> getAllByname(String softname) {
        QueryWrapper<Edition> wrapper = new QueryWrapper<>();
        wrapper.eq("softname_id",softname);
        wrapper.orderByDesc("time");
        List<Edition> editions = editionMapper.selectList(wrapper);
        return editions;
    }

    @Override
    public void downloadTime(String id) {
        int i = editionMapper.downloadTime(id);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100120);
        }
    }
}
