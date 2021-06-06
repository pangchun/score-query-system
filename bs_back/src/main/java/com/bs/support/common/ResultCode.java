package com.bs.support.common;

/**
 * 枚举了一些常用API操作码
 */
public enum ResultCode implements IErrorCode {
    //layui成功状态码为0
    SUCCESS(0, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
    private long code;
    private String msg;
    //枚举的构造方法默认就是私有的
    ResultCode(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}

