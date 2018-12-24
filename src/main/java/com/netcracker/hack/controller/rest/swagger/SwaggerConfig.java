package com.netcracker.hack.controller.rest.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket productApi() {

    ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

    apiInfoBuilder.title("SearchHack API");
    apiInfoBuilder.description("SearchHack API for NetCracker project");
    apiInfoBuilder.version("1.0");
    apiInfoBuilder.contact(
        new Contact("SearchHack team", "https://hacksearch.herokuapp.com", "ncteam2018@gmail.com"));
    apiInfoBuilder.license("Apache License Version 2.0");
    apiInfoBuilder.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0");

    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.netcracker.hack.controller.rest"))
        .paths(regex("/api.*"))
        .build()
        .apiInfo(apiInfoBuilder.build());

  }
}
