package com.bookservice.config;

import com.bookservice.constants.StringConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Class containing the configurations required for Swagger
 * @author NIHARIKA GADDE
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket libraryApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .tags(new Tag( StringConstants.BOOK_SWAGGER_TAG, StringConstants.BOOK_SWAGGER_TAG_DESC));
    }

    private ApiInfo metaData() {
        return new ApiInfo(
                StringConstants.BOOK_SERVICE_REST_API,
                StringConstants.SWAGGER_PROJECT_DESC,
                StringConstants.VERSION,
                StringConstants.TERMS_OF_SERVICE,
                new Contact( StringConstants.AUTHOR, StringConstants.PROJECT_URL, StringConstants.AUTHOR_EMAIL),
                StringConstants.APACHE_LICENSE,
                StringConstants.LICENSE_URL);
    }
}