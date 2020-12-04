package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("common_question")
public class CommonQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("常见问题id，自动创建不用输入")
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("解决方法")
    private String solution;

    @ApiModelProperty("多少人觉得有用")
    private Integer useful;

    @ApiModelProperty("多少人觉得没用")
    private Integer useless;


}
