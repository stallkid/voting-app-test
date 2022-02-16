package com.cooperativism.voting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String DEFAULT_INCLUDE_PATTERN = "/.*";
    private static final String BASE_PACKAGE = "com.cooperativism.voting.controller";
    private static final String CONTACT_NAME = "Renan Luis Bianchini";
    private static final String CONTACT_PORTFOLIO = "https://renan-portfolio.netlify.app/about";
    private static final String CONTACT_EMAIL = "renan.bianchini@southsystem.com.br";
    private static final String API_TITLE = "Desafio Técnico Backend - App de Votação";
    private static final String API_DESCRIPTION = "Esta Documentação abrange todas as APIs para o uso do projeto";
    private static final String API_LICENSE = "Apache 2.0";
    private static final String API_LICENSE_URL = "http://www.apache.org/licenses/LICENSE-2.0.html";
    private static final String API_VERSION = "1.0.0";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .build().apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo() {
        Contact contact = new Contact(CONTACT_NAME, CONTACT_PORTFOLIO, CONTACT_EMAIL);
        return new ApiInfoBuilder().title(API_TITLE)
                .description(API_DESCRIPTION)
                .contact(contact)
                .license(API_LICENSE)
                .licenseUrl(API_LICENSE_URL)
                .version(API_VERSION)
                .build();
    }

}
