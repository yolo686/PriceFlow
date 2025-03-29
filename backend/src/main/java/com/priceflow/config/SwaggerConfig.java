package com.priceflow.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("priceflow比价项目接口文档")
                        .description("软件工程课程大作业，实现用户实时监控商品价格的功能")
                        .version("v1"))
                .externalDocs(new ExternalDocumentation()
                        .description("小组成员：XXX、XXX、XX")
                        .url("/"));
    }
}
