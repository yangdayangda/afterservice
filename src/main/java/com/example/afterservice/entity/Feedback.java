package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 反馈者的ID
     */
    private String userId;

    /**
     * 对哪一个软件反馈
     */
    private String softwareName;

    /**
     * 反馈的类型
     */
    private String questionType;

    /**
     * 反馈的精简描述
     */
    private String briefDescribe;

    /**
     * 详细描述
     */
    private String declareDescribe;

    /**
     * 上传文件的路径
     */
    private String uploadFile;

    /**
     * 1表示未处理，2表示正在处理，3表示处理完毕
     */
    @TableField("isdeal")
    private Integer isdeal;

    /**
     * 反馈发出时间
     */
    private LocalDateTime time;

    /**
     * 处理人员的ID
     */
    private String dealUserId;

    /**
     * 处理方法
     */
    private String solution;


}
