package com.bs.support.exception;

import com.bs.support.common.IErrorCode;

/**
 * 业务层异常枚举类
 *
 * @author : zhangqianchun
 * @date : 2020-1-17 16:47
 * @description : 业务层异常枚举类
 * @version : v1.0
 */
public enum ServiceExceptionCode implements IErrorCode {

    /*系统错误*/
    SYSTEM_ERROR(10000, "系统错误"),
    /*参数错误*/
    PARAM_ERROR(20000, "参数错误"),
    /*数据不存在错误*/
    NOT_EXIST_ERROR(30000, "数据不存在错误");


    private final long code;
    private final String message;

    ServiceExceptionCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }
}
