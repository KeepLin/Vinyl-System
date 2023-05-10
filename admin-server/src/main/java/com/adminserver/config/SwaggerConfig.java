package com.adminserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        // 配置OAS 3.0协议
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.adminserver.controller"))
                .build()
                .apiInfo(createApiInfo())
                .enable(false);

    }

    @Bean
    public ApiInfo createApiInfo() {
        return new ApiInfo("AdminSystem Swagger",
                "后台管理接口文档",
                "5.0",
                "www.yyds.cn",
                new Contact("DragonLin","www.yyds.cn","DragonLin19@163.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
