package com.example.afterservice.service;

import com.example.afterservice.entity.UserGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
public interface UserGroupService {

    List<UserGroup> getAllUgroup(int pageIndex, int size);

    int getUgroupSize();

    void addUgroup(String name);

    void deleteUgroup(String name);
}
