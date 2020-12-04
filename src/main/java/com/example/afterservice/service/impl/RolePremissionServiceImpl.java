package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.entity.Premission;
import com.example.afterservice.entity.RolePremission;
import com.example.afterservice.mapper.RolePremissionMapper;
import com.example.afterservice.service.RolePremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RolePremissionServiceImpl implements RolePremissionService {

    @Autowired
    private RolePremissionMapper rolePremissionMapper;


    @Override
    public Set<String> getPreByRole(Set<String> roles) {
        Set<String> allPremission = new HashSet<>();
        for (String role :
                roles) {
            Set<String> premission = rolePremissionMapper.getPreByRole(role);
            allPremission.addAll(premission);
        }
        return allPremission;
    }

    @Override
    public void updatePremission(List<String> premissions, String role) {
        QueryWrapper<RolePremission> rolePremissionQueryWrapper = new QueryWrapper<>();
        rolePremissionQueryWrapper.eq("role_id",role);
        int delete = rolePremissionMapper.delete(rolePremissionQueryWrapper);

        for (String premission:
             premissions) {
            RolePremission rolePremission = new RolePremission(role, premission);
            rolePremissionMapper.insert(rolePremission);
        }

    }

    @Override
    public Set<Premission> getAllPreByRoles(Set<String> roles) {
        Set<Premission> set = new HashSet<>();
        for (String role:
             roles) {
            Set<Premission> allPreByRole = rolePremissionMapper.getAllPreByRole(role);
            set.addAll(allPreByRole);
        }
        return set;
    }

}
