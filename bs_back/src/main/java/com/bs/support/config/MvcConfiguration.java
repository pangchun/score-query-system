package com.bs.support.config;

import com.bs.support.util.FileUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * webMVC的自定义配置
 *
 * @author : zhangqianchun
 * @date : 2021-1-30 13:47
 * @description : 关于webMVC的自定义配置文件
 * @version : v1.0
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * 配置静态资源文件，实现图片上传回显;
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //获取文件的真实路径
        String path = FileUtils.PATH;

        //注意：这里的addResourceLocations不能用绝对路径，如：C:/user/...
        registry.addResourceHandler("/fileupload/**")
                .addResourceLocations("file:" + path);
    }
}
