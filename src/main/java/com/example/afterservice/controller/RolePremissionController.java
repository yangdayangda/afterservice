package com.example.afterservice.controller;

import com.example.afterservice.common.domain.RestResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("rolePremission")
public class RolePremissionController {

    @PostMapping("update")
    public RestResponse updateRolePre(@RequestBody Map<String,String> data){

        return new RestResponse(data);
    }
}
