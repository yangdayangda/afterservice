package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@AllArgsConstructor
@TableName("software")
public class Software implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前服务软件名称
     */
    @ApiModelProperty("软件名称")
    @TableId(value = "softname", type = IdType.ID_WORKER)
    private String softname;


}
