package com.example.afterservice.controller;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.dto.EditionDto;
import com.example.afterservice.entity.Edition;
import com.example.afterservice.utils.JWTUtil;
import com.sun.mail.imap.ResyncData;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import com.example.afterservice.service.EditionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("edition")
@RestController
@CrossOrigin
@Api( tags = "版本库处理相关接口")
public class EditionController {

    @Autowired
    private EditionService editionService;

    @ApiOperation("查询所有版本信息")
    @OperLog(operModul = "查询全部版本")
    @GetMapping()
    public RestResponse getAllEdition(){
        List<EditionDto> editions = editionService.getAllByname();
        return new RestResponse(editions);
    }

    @ApiOperation("根据标题查询一条信息，数据库信息合法才能查出数据")
    @GetMapping(value = "{title}")
    public RestResponse getByTitle(@PathVariable("title") String title){
        EditionDto edition=editionService.getOneByTitle(title);
        return new RestResponse(edition);
    }

    @ApiOperation("根据标题模糊查询信息")
    @GetMapping(path = "vague-select/{vagueTitle}")
    public RestResponse vagueSelect(@PathVariable("vagueTitle") String vagueTitle){
        List<EditionDto> editions = editionService.vagueSelect(vagueTitle);
        return new RestResponse(editions);
    }

    @ApiOperation("增加版本库")
    @RequiresPermissions("edition:add")
    @OperLog(operModul = "版本库处理",operType = "新增版本库")
    @PostMapping()
    public RestResponse addEdition(HttpServletRequest request, Edition edition){
        String token = request.getHeader("Authorization");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        edition.setUserId(id);
        editionService.addEdition(edition);
        log.warn("- 管理员添加了软件版本{}",edition);
        return new RestResponse();
    }


    @ApiOperation("根据版本ID删除该条记录")
    @OperLog(operModul = "版本库处理",operType = "删除版本库")
    @DeleteMapping(value = "{id}")
    public RestResponse deleteEdition(@PathVariable("id") String id){
        editionService.deleteEdition(id);
        log.warn("- 管理员删除了ID为{}的软件版本",id);
        return new RestResponse();
    }


}
