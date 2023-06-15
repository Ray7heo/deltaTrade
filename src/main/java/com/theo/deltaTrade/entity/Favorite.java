package com.theo.deltaTrade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName favorite
 */
@TableName(value = "favorite", resultMap = "BaseResultMap")
@Data
public class Favorite implements Serializable {
    /**
     * 收藏id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}