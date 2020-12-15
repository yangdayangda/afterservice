package com.example.afterservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.afterservice.Aop.OperLog;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.entity.OperationLog;
import com.example.afterservice.service.impl.OperationLogService;
import com.example.afterservice.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "操作日志相关接口")
@RestController
@CrossOrigin
@RequestMapping("operlog")
public class OperLogController {
    @Autowired
    private OperationLogService operationLogService;

    @ApiOperation("分页查询所有日志，时间可以不填，表示全部查询")
    @GetMapping()
    public RestResponse getAll(PageVo pageVo){
        IPage<OperationLog> operationLogs = operationLogService.getAll(pageVo);
        return new RestResponse(operationLogs);
    }


    @ApiOperation("通过ID删除日志，可以批量删除")
    @OperLog(operModul = "日志处理",operType = "删除日志")
    @DeleteMapping()
    public RestResponse deleteById(@RequestParam(value = "id") List<String> id){
        int i = operationLogService.deleteById(id);
        return new RestResponse();
    }
}
