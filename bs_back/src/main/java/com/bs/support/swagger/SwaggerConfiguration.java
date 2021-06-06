package com.bs.support.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//TODO: 为Swagger的请求配置Token；

/**
 * 开启Swagger2
 *
 * @author : zhangqianchun
 * @date : 2021-1-14 12:46
 * @description : 访问地址：http://localhost:端口号/swagger-ui.html
 * @version : v1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /**
     * 配置了Swagger的Docket的bean实例
     *
     */
    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("bs_back")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RequestMapping.class))
                .build();
    }

    /**
     * 配置Swagger信息-apiInfo
     *
     */
    public ApiInfo apiInfo() {

        return new ApiInfo(
                "bs_back API",
                "毕业设计-成绩查询",
                "v1.0",
                "https://gitee.com/dongjixue/bs_back",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()
        );
    }

    /**
     * 文档作者信息
     */
    Contact contact = new Contact(

            "jiangxin、zhangcunkai、zhangqianchun",
            "https://gitee.com/dongjixue/bs_back",
            ""
    );
}
