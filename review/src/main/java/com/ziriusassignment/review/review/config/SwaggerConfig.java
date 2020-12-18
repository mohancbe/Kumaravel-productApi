package com.ziriusassignment.review.review.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)//
        .select()//
        .apis(RequestHandlerSelectors.basePackage("com.ziriusassignment"))//
        .paths(PathSelectors.any())//
        .build()//
        .apiInfo(metadata())//
        .securitySchemes(Collections.singletonList(apiKey()))//
        .securityContexts(Collections.singletonList(securityContext()));
  }

  private ApiInfo metadata() {
    return new ApiInfoBuilder()//
        .title("Review Microservice API")//
        .description(
            "Using this Reviews APIs, you can add reviews under specific review group "
            + "You can also create review group. To access most of the APIs, you need JWT"
            + "token. Once you have obtained the token, you should click on the "
            + "right top button `Authorize` and provide the token.")//
        .version("1.0.0")//
        .license("MIT License").licenseUrl("http://opensource.org/licenses/MIT")//
        .contact(new Contact(null, null, "kumar.okm1995@gmail.com"))//
        .build();
  }

  private ApiKey apiKey() {
    return new ApiKey("Authorization", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
  }
}
