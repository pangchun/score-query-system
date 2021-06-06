package com.bs.support.exception;

import com.bs.support.common.IErrorCode;

/**
 * 业务层统一异常类
 *
 * @author : zhangqianchun
 * @date : 2020-1-17 16:33
 * @description : 定义异常类，用于抛出异常，并交给ExceptionHandler管理
 * @version : v1.0
 */
public class ServiceException extends RuntimeException{

private IErrorCode iErrorCode;
private Object[] args;

    /**
     * 最终执行的构造方法，其他构造方法都会转到这里处理；
     * super(message) => 设置错误信息,message是父类Throwable的属性：detailMessage
     *
     * @param iErrorCode
     * @param message
     */
    public ServiceException (IErrorCode iErrorCode, String message) {

    super(message);
    this.iErrorCode = iErrorCode;
}

    public ServiceException (IErrorCode iErrorCode) {
        this(iErrorCode, iErrorCode.getMsg());
    }

    /**
     *
     * @param iErrorCode
     * @param args 可变参数，可以添加多个错误消息；
     */
    public ServiceException (IErrorCode iErrorCode, Object... args) {
        this(iErrorCode);
        this.args = args;
    }

    /* getter、setter */

    public IErrorCode getiErrorCode() {
        return iErrorCode;
    }

    public void setiErrorCode(IErrorCode iErrorCode) {
        this.iErrorCode = iErrorCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
