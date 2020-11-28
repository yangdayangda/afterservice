package com.example.afterservice.mapper;

import com.example.afterservice.entity.Edition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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

    @Update("update edition set download_num = download_num+1 where id =#{id}")
    int downloadTime(String id);
}
