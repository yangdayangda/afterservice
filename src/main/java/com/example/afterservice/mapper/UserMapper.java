package com.example.afterservice.mapper;

import com.example.afterservice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT NAME FROM user,role,user_role WHERE user.id=#{id} AND user_role.user_id = user.id AND user_role.role_id=role.id;")
    Set<String> getRoleByUser(String id);


    @Select("SELECT premissions.name FROM role,premissions,role_pre WHERE role.name=#{role} AND role_pre.role_id = role.name AND role_pre.premission_id = premissions.id;")
    Set<String> getPreByRole(String role);
}
