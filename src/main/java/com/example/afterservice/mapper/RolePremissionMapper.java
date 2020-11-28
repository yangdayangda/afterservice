package com.example.afterservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.afterservice.entity.RolePremission;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface RolePremissionMapper extends BaseMapper<RolePremission> {

    @Select("SELECT premissions.name FROM role,premissions,role_pre WHERE role.name=#{role} AND role_pre.role_id = role.name AND role_pre.premission_id = premissions.id;")
    Set<String> getPreByRole(String role);
}
