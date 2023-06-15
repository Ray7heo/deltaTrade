package com.theo.deltaTrade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

/**
 * 
 * @TableName commodity
 */
@TableName(value ="commodity", resultMap = "BaseResultMap")
@Data
public class Commodity implements Serializable {
    /**
     * 商品id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品名字
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品图片
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> images;

    /**
     * 卖家id
     */
    private Long sellerId;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 逻辑字段
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

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
     * 是否售出
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer isSold;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 所属用户
     */
    @TableField(exist = false)
    private User seller;
}