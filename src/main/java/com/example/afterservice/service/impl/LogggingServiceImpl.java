package com.example.afterservice.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.entity.Logging;
import com.example.afterservice.mapper.LoggingMapper;
import com.example.afterservice.service.LoggingService;

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
public class LogggingServiceImpl  implements LoggingService {

    @Autowired
    private LoggingMapper loggingMapper;

    @Override
    public List<Logging> getAll(int pageIndex,int size) {
        QueryWrapper<Logging> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("created_time");
        Page<Logging> page = new Page<>(pageIndex, size);
        IPage<Logging> iPage = loggingMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    @Override
    public int getAllCounts() {
        return loggingMapper.selectCount(null);
    }
}
