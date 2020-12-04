package com.example.afterservice.mapper;

import com.example.afterservice.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.afterservice.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
    @Select("SELECT user.* FROM user_role,user \n" +
            "WHERE user.id = user_role.user_id \n" +
            "AND user_role.role_id =(SELECT id FROM role \n" +
            "WHERE remark=(SELECT software_name FROM feedback \n" +
            "WHERE id=#{id}))")
    List<User> getUserById(String feedBackId);
}
