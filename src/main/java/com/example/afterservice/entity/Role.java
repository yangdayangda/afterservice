package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role")
public class Role {

    @TableId(value = "id", type = IdType.UUID)
    String id;
    String name;
    String remark;
}
