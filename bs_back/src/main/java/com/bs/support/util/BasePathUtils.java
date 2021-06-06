package com.bs.support.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * 项目路径获取工具
 *
 * @author : zhangqianchun
 * @date : 2021-1-30 13:16
 * @description : 通过拼装来获取项目路径，方便端口修改和代码维护
 * @version : v1.0
 */
@Slf4j
public class BasePathUtils {
    public static String getBasePath(HttpServletRequest request) {

        String scheme = request.getScheme();

        String serverName = request.getServerName();

        String port = String.valueOf(request.getServerPort());

        String contextPath = request.getContextPath();

        String basePath = scheme + "://" + serverName + ":" + port + "/" + contextPath;

        log.info(basePath);

        return basePath;
    }
}
