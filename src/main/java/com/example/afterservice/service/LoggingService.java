package com.example.afterservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.afterservice.entity.Logging;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
public interface LoggingService  {

    List<Logging> getAll(int pageIndex,int size);

    int getAllCounts();

}
