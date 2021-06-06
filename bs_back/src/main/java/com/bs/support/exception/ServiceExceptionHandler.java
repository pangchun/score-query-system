package com.bs.support.exception;

import cn.hutool.json.JSONUtil;
import com.bs.support.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常处理器
 *
 * @author : zhangqianchun
 * @date : 2020-1-17 17:04
 * @description : 所有的自定义异常需要throw时，都自动来到这里处理，返回Json数据
 * @version : v1.0
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    private static final Logger log =LoggerFactory.getLogger(ServiceExceptionHandler.class);

    /** 处理ServiceException异常，返回Json数据 */
    @ExceptionHandler({ServiceException.class})
    public CommonResult<String> handelServiceException(ServiceException ex) {

        log.error("ServiceException", ex);

        return CommonResult.error(ex.getiErrorCode().getCode(), ex.getMessage());

    }

    /**
     * 权限异常处理
     *
     * @Author jiangxin
     * @param response
     * @param ex
     * @throws IOException
     */
    @ExceptionHandler({AccessDeniedException.class})
    public void handelAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) throws IOException {

        log.error("ServiceException", ex.getMessage());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.forbidden(ex.getMessage())));
        response.getWriter().flush();

    }



}
