package com.fei.common.enums;

import com.fei.common.result.IResponseStatus;
import com.fei.common.result.Result;
import com.fei.common.result.ResultAble;

/**
 * @author ZhangPengFei
 * @description
 */
public enum ReturnStatusEnum implements ResultAble, IResponseStatus {
    SUCCESS(200, "success");
    private final int code;
    private final String message;

    ReturnStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Result toResult() {
        return Result.error(this.code, this.message);
    }

    @Override
    public Result toResult(Object... args) {
        return Result.error(this.code, this.message, args);
    }
}
