package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("role_pre")
@NoArgsConstructor
public class RolePremission {
    @ApiModelProperty("角色资源连接表id")
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    @ApiModelProperty("角色的名称")
    private String roleId;

    @ApiModelProperty("权限的ID")
    private String premissionId;

    public RolePremission(String roleId, String premissionId) {
        this.roleId = roleId;
        this.premissionId = premissionId;
    }
}
