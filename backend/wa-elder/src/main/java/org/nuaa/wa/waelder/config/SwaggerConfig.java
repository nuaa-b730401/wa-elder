package org.nuaa.wa.waelder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Name: SwaggerConfig
 * @Description: TODO
 * @Author: tomax
 * @Date: 2019-10-24 11:49
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(restApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.nuaa.wa.waelder.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo restApiInfo() {
        return new ApiInfoBuilder()
                .title("wa-elder 后台接口文档")
                .description("restful 风格文档描述")
                .termsOfServiceUrl("no terms of serviceUrl")
                .version("0.9")
                .build();
    }
}
