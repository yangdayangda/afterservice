package com.example.afterservice.service;

import com.example.afterservice.dto.EditionDto;
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

    List<EditionDto> getAllByname();


    void deleteEdition(String id);

    EditionDto getOneByTitle(String title);

    List<EditionDto> vagueSelect(String title);
}
