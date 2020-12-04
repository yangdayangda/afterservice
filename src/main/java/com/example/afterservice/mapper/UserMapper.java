package com.example.afterservice.mapper;

import com.example.afterservice.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.afterservice.entity.UserDto;
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

    @Select("SELECT user.*,role.name,remark FROM user,user_role,role WHERE user.id=user_role.user_id AND role.id = user_role.role_id")
    List<UserDto> getAllUser();
}
