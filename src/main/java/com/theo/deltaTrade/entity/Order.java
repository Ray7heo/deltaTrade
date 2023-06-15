package com.theo.deltaTrade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName order
 */
@TableName(value = "`order`", resultMap = "BaseResultMap")
@Data
public class Order implements Serializable {
    /**
     * 订单id
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
     * 卖家id
     */
    private Long sellerId;

    /**
     * 买家id
     */
    private Long buyerId;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑字段
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}