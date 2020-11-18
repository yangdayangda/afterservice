package com.example.afterservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@TableName("logs")
public class Logs implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * 操作人员的ID
     */
    private String userId;

    /**
     * 操作时间
     */
    private LocalDateTime time;

    /**
     * 操作类型
     */
    private String workType;


}
