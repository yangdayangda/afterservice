package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("role")
public class Role {

    @ApiModelProperty("角色ID")
    @TableId(value = "id", type = IdType.UUID)
    private String id;
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("该角色备注")
    private String remark;

    @ApiModelProperty("角色描述")
    private String comment;
}
