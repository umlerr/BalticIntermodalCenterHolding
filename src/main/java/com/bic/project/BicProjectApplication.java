package com.bic.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;



@SpringBootApplication
@ConfigurationPropertiesScan
public class BicProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(BicProjectApplication.class, args);
    }
}

