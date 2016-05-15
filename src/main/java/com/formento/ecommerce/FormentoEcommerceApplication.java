package com.formento.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
public class FormentoEcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormentoEcommerceApplication.class, args);
    }

}
