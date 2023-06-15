package com.theo.deltaTrade.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 微信用户唯一标识
     */
    private String openId;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名
     */
    @TableField(fill = FieldFill.INSERT)
    private String name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户电话号码
     */
    private String phoneNumber;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 逻辑字段
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 验证码
     */
    @TableField(exist = false)
    private String  code;

}