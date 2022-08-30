package com.redelles.tasks.configuration.swagger;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
            .group("tasks")
            .packagesToScan("com.redelles.tasks")
            .build();
    }

    @Bean
    public OpenAPI getApiInformation() {
        return new OpenAPI()
            .info(new Info()
                .title("Tasks REST API")
                .description("REST API to get information about tasks app")
                .version("0.0.1-SNAPSHOT")
                .termsOfService("API Terms of Service URL")
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                .contact(new Contact()
                    .name("FinSoft")
                    .url("Without url")
                    .email("javidesoft@gmail.com")));
    }
}
