package com.bs.support.util;

import com.bs.support.common.IErrorCode;
import com.bs.support.exception.ServiceException;

/**
 * 断言工具类
 *
 * @author : zhangqianchun
 * @date : 2020-1-17 17:30
 * @description : 公用得工具类，用于对前端传来的数据进行判断；可以在下方继续添加公用的断言；
 * @version : v1.0
 */
public class AssertUtils {


    public AssertUtils() {
    }

    public static void notNull(Object object, IErrorCode iErrorCode) throws ServiceException {
        if (object == null) {
            throw new ServiceException(iErrorCode);
        }
    }

    public static void notNull(Object object, IErrorCode iErrorCode, String message) throws ServiceException {
        if (object == null) {
            throw new ServiceException(iErrorCode,message);
        }
    }

    public static void notNull(Object object, IErrorCode iErrorCode, Object... args) throws ServiceException {
        if (object == null) {
            throw new ServiceException(iErrorCode,args);
        }
    }
}
