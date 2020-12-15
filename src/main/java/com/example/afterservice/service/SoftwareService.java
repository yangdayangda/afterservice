package com.example.afterservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.afterservice.entity.Software;
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
public interface SoftwareService {

    IPage<Software> getAll(int pageIndex, int size);

    void addSoftware(String name);

    void deleteSoftware(String name);
}
