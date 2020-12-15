package com.example.afterservice.mapper;

import com.example.afterservice.dto.EditionDto;
import com.example.afterservice.entity.Edition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
public interface EditionMapper extends BaseMapper<Edition> {


    @Select("SELECT edition.*,username FROM edition,user WHERE edition.user_id=user.id")
    List<EditionDto> selectAll();

    @Select("SELECT edition.*,username FROM edition,user WHERE title=#{title} AND edition.user_id=user.id")
    EditionDto getOneByTitle(String title);

    @Select("SELECT edition.*,username FROM edition,user WHERE title LIKE #{title} AND edition.user_id=user.id")
    List<EditionDto> vagueSelect(String title);
}
