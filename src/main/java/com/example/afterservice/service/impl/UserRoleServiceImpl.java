package com.example.afterservice.service.impl;

import com.example.afterservice.mapper.UserRoleMapper;
import com.example.afterservice.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public Set<String> getRoleById(String id) {
        return userRoleMapper.getRoleByUser(id);
    }
}
