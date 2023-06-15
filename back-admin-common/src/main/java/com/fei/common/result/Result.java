package com.fei.common.result;

import com.fei.common.page.Page;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.Collections;

public class Result<T> implements Serializable {

    private int status = 200;
    private String message = "success";

    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    public static <T> Result<T> unKnowErr(String message, Object... args) {
        return Result.error(5000, message, args);
    }


    public static <T> Result<T> error(Integer code, String errMsg, Object... args) {
        for (Object arg : args) {
            errMsg = MessageFormat.format(errMsg, args);
        }
        return new Result<>(code, errMsg);
    }

    public static <T> Result<T> error(Integer code, String errMsg) {
        return new Result<>(code, errMsg);
    }

    public static <T> Result<T> error(IResponseStatus status) {
        return new Result<>(status);
    }

    public static <T> Result<T> error(Result result) {
        return new Result<>(result.getStatus(), result.getMessage());
    }

    public Result(int status, String message) {
        this.setStatus(status);
        this.message = message;
    }

    public Result(IResponseStatus status) {
        this.setStatus(status.getValue());
        this.message = status.getName();
    }

    public Result(int status, String message, T data) {
        this.setStatus(status);
        this.message = message;
        this.data = data;
    }

    public boolean ok() {
        return status == 200;
    }

    public boolean success() {
        return status == 200 && this.data != null;
    }

    public boolean successAndNotEmpty() {
        if (data instanceof Collection) {
            return status == 200 && !CollectionUtils.isEmpty((Collection) data);
        }
        if (data instanceof Page) {
            return status == 200 && !CollectionUtils.isEmpty(((Page<?>) data).getPage());
        }
        return status == 200 && this.data != null;

    }

    public static <T> Result<Page<T>> emptyPageResult() {
        return new Result<>(new Page<>(0, Collections.emptyList()));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}



