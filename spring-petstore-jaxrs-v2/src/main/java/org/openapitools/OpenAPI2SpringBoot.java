package org.openapitools;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@SpringBootApplication(exclude = {
        EmbeddedMongoAutoConfiguration.class
})
@ComponentScan(basePackages = {"org.openapitools", "org.openapitools.api", "org.openapitools.configuration"})
@ServletComponentScan(basePackages = {"org.openapitools", "org.openapitools.api", "org.openapitools.configuration"})
@Configuration
@ApplicationPath("/")
@EnableMongoRepositories(basePackages = {"org.openapitools.dao"})
@ComponentScan
public class OpenAPI2SpringBoot extends Application implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(OpenAPI2SpringBoot.class).run(args);
    }

    static class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }
    }

    @Bean
    public WebMvcConfigurer webConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns("*")
                        .allowedOrigins("http://localhost:8080", "http://localhost:4200", "http://localhost:27017")
                        .allowedMethods("GET", "POST", "OPTIONS", "PUT")
                        .allowedHeaders("Content-Type", "X-Requested-With", "Accept", "Origin", "Access-Control-Request-Method",
                                "Access-Control-Request-Headers", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
