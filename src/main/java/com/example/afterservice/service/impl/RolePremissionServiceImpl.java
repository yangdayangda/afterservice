package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.afterservice.entity.Premission;
import com.example.afterservice.entity.RolePremission;
import com.example.afterservice.mapper.RolePremissionMapper;
import com.example.afterservice.service.RolePremissionService;
import com.example.afterservice.shiro.realm.Jwtrealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
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

    /**
     * 修改相应角色的权限
     * @param premissions
     * @param role
     */
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
        log.warn("- 管理员修改了{}的权限",role);
//        更改权限成功以后退出登陆状态清空缓存
        SecurityUtils.getSubject().logout();
    }

    /**
     * 得到角色的所有权限信息
     * @param roles
     * @return
     */
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
