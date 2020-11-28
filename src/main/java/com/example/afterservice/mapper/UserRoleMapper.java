package com.example.afterservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.afterservice.entity.UserRole;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("SELECT NAME FROM user,role,user_role WHERE user.id=#{id} AND user_role.user_id = user.id AND user_role.role_id=role.id;")
    Set<String> getRoleByUser(String id);

}
