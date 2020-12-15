package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.Role;
import com.example.afterservice.entity.RolePremission;
import com.example.afterservice.mapper.RoleMapper;
import com.example.afterservice.mapper.RolePremissionMapper;
import com.example.afterservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePremissionMapper rolePremissionMapper;

    @Override
    public List<Role> getAllRole() {
        return roleMapper.selectList(null);
    }

    @Override
    public void addRole(Role role) {
        int i = roleMapper.insert(role);
        for (int j = 0; j < 6; j++) {
            rolePremissionMapper.insert(new RolePremission(role.getName(),String.valueOf(j)));
        }
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100118);
        }
    }

    @Override
    public void deleteRole(String name) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("name",name);
        int i = roleMapper.delete(wrapper);
        QueryWrapper<RolePremission> rolePremissionQueryWrapper = new QueryWrapper<>();
        rolePremissionQueryWrapper.eq("role_id",name);
        rolePremissionMapper.delete(rolePremissionQueryWrapper);
        if (i==0){
            throw new BusinessException(CommonErrorCode.E_100119);
        }
    }
}
