package com.kakaopay.issuebarcodeapi.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${swagger.url}")
    String url;

    private ApiInfo swaggerInfo() {
        return new ApiInfoBuilder()
                .title("Kakaopay-IssueBarcode-API")
                .description("Kakaopay-IssueBarcode API Docs")
                .version("0.0.1-dev")
                .build();
    }


//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.kakaopay.issuebarcodeapi"))
//                .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/")
//                .apiInfo(swaggerInfo());
//    }
    @Bean
    public Docket swaggerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(url).pathMapping("/")
                .apiInfo(swaggerInfo()).select()
                 // 해당 패키지 이하로 모두적용.
                .apis(RequestHandlerSelectors.basePackage("com.kakaopay.issuebarcodeapi"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

}
