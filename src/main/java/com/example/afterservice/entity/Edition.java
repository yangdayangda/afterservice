package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("edition")
public class Edition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本库ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 属于哪一个软件
     */
    private String softwareId;

    /**
     * 版本号
     */
    private String versionNumber;

    /**
     * 版本描述
     */
    private String describe;

    /**
     * 该版本文件所在路径
     */
    private String fileLink;

    /**
     * 下载次数
     */
    private Integer downloadNum;

    /**
     * 发布时间
     */
    private LocalDateTime time;


}
