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

}
