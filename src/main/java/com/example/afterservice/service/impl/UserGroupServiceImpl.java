package com.example.afterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.entity.UserGroup;
import com.example.afterservice.mapper.UserGroupMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.afterservice.service.UserGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Slf4j
@Service
@Transactional
public class UserGroupServiceImpl  implements UserGroupService {

    @Autowired
    private UserGroupMapper userGroupMapper;

    @Override
    public List<UserGroup> getAllUgroup(int pageIndex, int size) {
        Page<UserGroup> page = new Page<>(pageIndex,size);
        IPage<UserGroup> iPage = userGroupMapper.selectPage(page, null);
        List<UserGroup> records = iPage.getRecords();
        return records;
    }

    @Override
    public int getUgroupSize() {
        return userGroupMapper.selectCount(null);
    }

    @Override
    public void addUgroup(String name) {
        Integer count = userGroupMapper.selectCount(new QueryWrapper<UserGroup>().eq("duty", name));
        if (count != 0){
            throw new BusinessException(CommonErrorCode.E_100117);
        }
        userGroupMapper.insert(new UserGroup(name));
    }

    @Override
    public void deleteUgroup(String name) {
        userGroupMapper.delete(new QueryWrapper<UserGroup>(new UserGroup(name)));
    }
}
