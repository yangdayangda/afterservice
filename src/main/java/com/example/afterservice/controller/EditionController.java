package com.example.afterservice.controller;


import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.Edition;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.EditionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2020-11-15
 */
@Slf4j
@RestController
@CrossOrigin
@Api(value = "", tags = "", description="")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @ApiOperation("增加版本库")
    @PostMapping("add")
    public RestResponse addEdition(Edition edition){
        editionService.addEdition(edition);
        return new RestResponse();
    }

    @ApiOperation("得到全部的版本库，传入需要版本库对应的名字")
    @GetMapping("getAll")
    public RestResponse getAllEdition(String softname){
        List<Edition> editions = editionService.getAllByname(softname);
        return new RestResponse(editions);
    }

    @ApiOperation("点击下载，下载次数加一，传入该版本对应的版本ID")
    @GetMapping("downloadNum")
    public RestResponse downloadTime(String id){
        editionService.downloadTime(id);
        return new RestResponse();
    }
}
