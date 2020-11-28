package com.example.afterservice.service.impl;

import com.example.afterservice.mapper.RolePremissionMapper;
import com.example.afterservice.service.RolePremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
}
