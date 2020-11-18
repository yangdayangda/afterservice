package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.entity.Software;
import com.example.afterservice.mapper.SoftwareMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.service.SoftwareService;
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
public class SoftwareServiceImpl  implements SoftwareService {

    @Autowired
    private SoftwareMapper softwareMapper;
    @Override
    public int getCount() {
        return softwareMapper.selectCount(null);
    }

    @Override
    public List<Software> getAll(int pageIndex, int size) {
        Page<Software> softwarePage = new Page<>(pageIndex, size);
        IPage<Software> iPage = softwareMapper.selectPage(softwarePage, null);
        List<Software> records = iPage.getRecords();
        return records;
    }

    @Override
    public void addSoftware(String name) {
        softwareMapper.insert(new Software(name));
    }

    @Override
    public void deleteSoftware(String name) {
        softwareMapper.delete(new QueryWrapper<Software>(new Software(name)));
    }
}