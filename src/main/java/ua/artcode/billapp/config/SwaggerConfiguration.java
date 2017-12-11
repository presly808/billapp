package ua.artcode.billapp.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("ua.artcode.billapp.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(true);
    }

    @Bean
    UiConfiguration uiConfiguration(){
        return new UiConfiguration(
                "validatorUrl",
                "none",
                "alpha",
                "schema",
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                true,
                true,
                60000L);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "BillApp API",
                "This API implemented by Artcode team",
                "API 0.1",
                "Api for BillApp",
                new Contact("Artcode","art-code.com.ua", "artcodekiev@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}


