package com.theo.deltaTrade.common;

import lombok.Getter;

@Getter
public enum ApiMsg {
    SUCCESS(200, "成功"),
    FAIL(400, "失败"),

    USER_NOT_EXIST(1001, "用户不存在"),
    EMAIL_USED(1002, "邮箱已注册"),
    PASSWORD_WRONG(1003, "密码错误"),
    CODE_WRONG(1004, "验证码错误"),

    CATEGORY_NOT_EXIST(2001, "分类不存在"),

    COMMODITY_NOT_EXIST(3001, "商品不存在"),

    FAVORITE_NOT_EXIST(4001, "收藏不存在"),

    ORDER_NOT_EXIST(5001, "订单不存在"),
    ORDER_BUYER_WRONG(5002, "买家不正确"),

    FILE_UPLOAD_FAIL(6001, "文件上传失败");

    private Integer code;
    private String msg;

    ApiMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
