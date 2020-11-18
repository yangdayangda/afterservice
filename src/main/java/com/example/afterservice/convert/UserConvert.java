package com.example.afterservice.convert;


import com.example.afterservice.entity.User;
import com.example.afterservice.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);
    User vo2entity(UserVo userVo);
    UserVo entity2vo(User user);
}
