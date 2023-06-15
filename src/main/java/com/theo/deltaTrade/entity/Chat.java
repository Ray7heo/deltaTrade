package com.theo.deltaTrade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName chat
 */
@TableName(value ="chat", resultMap = "BaseResultMap")
@Data
public class Chat implements Serializable {
    /**
     * 聊天记录id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 商品
     */
    @TableField(exist = false)
    private Commodity commodity;

    /**
     * 发送者id
     */
    private Long fromId;

    /**
     * 发送者实体
     */
    private User from;

    /**
     * 接收者id
     */
    private Long toId;

    /**
     * 接收者
     */
    @TableField(exist = false)
    private User to;

    /**
     * 聊天内容
     */
    private String content;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 逻辑字段
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}