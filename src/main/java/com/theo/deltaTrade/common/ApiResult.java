package com.theo.deltaTrade.common;

import lombok.Data;


@Data
public class ApiResult {
    private final Integer code;
    private final String msg;
    private final Object data;

    public ApiResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ApiResult success(Object data) {
        ApiMsg success = ApiMsg.SUCCESS;
        return new ApiResult(success.getCode(), success.getMsg(), data);
    }

    public static ApiResult success() {
        ApiMsg success = ApiMsg.SUCCESS;
        return new ApiResult(success.getCode(), success.getMsg(), null);
    }

    public static ApiResult fail(ApiMsg msg) {
        return new ApiResult(msg.getCode(), msg.getMsg(), null);
    }

    public static ApiResult fail() {
        ApiMsg fail = ApiMsg.FAIL;
        return new ApiResult(fail.getCode(), fail.getMsg(), null);
    }
}
