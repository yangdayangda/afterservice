package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_pre")
public class RolePremission {
    @TableId(value = "id",type = IdType.UUID)
    String id;
    String roleId;
    String premissionId;
}
