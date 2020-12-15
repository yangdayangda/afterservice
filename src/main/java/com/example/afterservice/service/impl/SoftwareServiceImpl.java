package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.entity.Role;
import com.example.afterservice.entity.Software;
import com.example.afterservice.entity.UserRole;
import com.example.afterservice.mapper.RoleMapper;
import com.example.afterservice.mapper.SoftwareMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.mapper.UserRoleMapper;
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

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<Software> getAll(int pageIndex, int size) {
        Page<Software> softwarePage = new Page<>(pageIndex, size);
        IPage<Software> iPage = softwareMapper.selectPage(softwarePage, null);
        return iPage;
    }

    @Override
    public void addSoftware(String name) {
        softwareMapper.insert(new Software(name));
        Role role = new Role();
        roleMapper.insert(role);
        role.setName("programmer");
        roleMapper.insert(role);
    }

    @Override
    public void deleteSoftware(String name) {
        softwareMapper.delete(new QueryWrapper<Software>(new Software(name)));
    }
}
