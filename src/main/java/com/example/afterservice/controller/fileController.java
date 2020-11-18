package com.example.afterservice.controller;

import com.example.afterservice.common.domain.BusinessException;
import com.example.afterservice.common.domain.CommonErrorCode;
import com.example.afterservice.common.domain.RestResponse;
import com.example.afterservice.utils.QiniuUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/file")
public class fileController {

    @ApiOperation("上传文件，返回文件的地址")
    @PostMapping("/upload")
    public RestResponse uploadfile(MultipartFile file){
        String  name =UUID.randomUUID().toString();
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.indexOf('.') - 1);
        name = name +substring;
        String url;
        try{
            url = QiniuUtils.upload(file.getBytes(), name);
        }catch (Exception e){
            throw new BusinessException(CommonErrorCode.E_100106);
        }
        return new RestResponse(url);
    }
}
