package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("premissions")
public class Premission {
    @ApiModelProperty("权限id")
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Integer id;

    @ApiModelProperty("权限的名称")
    private String name;

    @ApiModelProperty("权限保留字段")
    private String remarks;


}
