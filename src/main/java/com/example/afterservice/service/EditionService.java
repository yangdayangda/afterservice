package com.example.afterservice.service;

import com.example.afterservice.entity.Edition;
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
public interface EditionService  {

    void addEdition(Edition edition);

    List<Edition> getAllByname(String softname);

    void downloadTime(String id);
}
