package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("role_pre")
@NoArgsConstructor
public class RolePremission {
    @TableId(value = "id",type = IdType.UUID)
    String id;
    String roleId;
    String premissionId;

    public RolePremission(String roleId, String premissionId) {
        this.roleId = roleId;
        this.premissionId = premissionId;
    }
}
