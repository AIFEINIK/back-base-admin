package com.fei.common.enums;

import com.fei.common.result.IResponseStatus;
import com.fei.common.result.Result;
import com.fei.common.result.ResultAble;

/**
 * @author ZhangPengFei
 * @description
 */
public enum ReturnStatusEnum implements ResultAble, IResponseStatus {
    SUCCESS(200, "success"),
    SYS_USER_NOT_FOUND(1000, "登录用户：{0} 不存在"),
    SYS_USER_ACCOUNT_STATUS_INVALID(1001, "登录用户：{0} 账号状态非法"),
    SYS_USER_PWD_INVALID(1002, "登录用户：{0} 密码不正确"),
    ;
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

    public String getMessage() {
        return message;
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
