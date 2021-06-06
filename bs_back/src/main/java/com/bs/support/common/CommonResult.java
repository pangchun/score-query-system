package com.bs.support.common;

/**
 * 通用返回对象
 * 2020/7/6
 */
public class CommonResult<T> {
    //layui需要的返回格式
    private long code;
    private String msg="";//信息
    private Long count=0L;//数量
    private T data;

    protected CommonResult() {
    }


    protected CommonResult(long code, String msg, T data,Long count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count=count;
    }
    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data,Long count) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data,count);
    }
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg(), data,null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  msg 提示信息
     */
    public static <T> CommonResult<T> success(T data, String msg) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), msg, data,null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMsg(), null,null);
    }

    /**
     * 失败返回结果
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), msg, null,null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param msg 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String msg) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), msg, null,null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMsg(), data,null);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMsg(), data,null);
    }

    /**
     * 业务层发生错误返回的结果
     *
     * @Author zhangqianchun
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> error(long code, String message) {
        CommonResult<T> result = new CommonResult();
        result.code = code;
        result.msg = message;
        return result;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

