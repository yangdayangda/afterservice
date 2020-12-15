package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.entity.OperationLog;
import com.example.afterservice.mapper.OperLogMapper;
import com.example.afterservice.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationLogService {
    @Autowired
    private OperLogMapper operLogMapper;

    public void insert(OperationLog operlog) {
        operLogMapper.insert(operlog);
    }

    public IPage<OperationLog> getAll(PageVo pageVo) {
        Page<OperationLog> page = new Page<>(Long.valueOf(pageVo.getPageIndex()),Long.valueOf(pageVo.getSize()));
        QueryWrapper<OperationLog> wrapper = new QueryWrapper<>();
        wrapper.between("oper_create_time",pageVo.getStartTime(),pageVo.getEndTime());
        wrapper.orderByDesc("oper_create_time");
        IPage<OperationLog> iPage = operLogMapper.selectPage(page, wrapper);
        return iPage;
    }


    public int deleteById(List<String> id) {
        int i = operLogMapper.deleteBatchIds(id);
        return i;
    }
}
