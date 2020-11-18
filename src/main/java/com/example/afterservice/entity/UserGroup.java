package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("user_group")
@AllArgsConstructor
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户组中的职责
     */
    @TableId(value = "duty", type = IdType.ID_WORKER)
    private String duty;


}
