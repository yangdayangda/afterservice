package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("user")
public class UserDto extends User implements Serializable {

    @ApiModelProperty("用户的角色")
    private String name;

    @ApiModelProperty("用户的角色的备注")
    private String remark;
}
