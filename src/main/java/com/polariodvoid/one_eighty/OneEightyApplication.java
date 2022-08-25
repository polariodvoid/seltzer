package com.polariodvoid.one_eighty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com.polariodvoid.one_eighty"})
@EntityScan("com.polariodvoid.one_eighty.Model")
@EnableJpaRepositories("com.polariodvoid.one_eighty")

public class OneEightyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(OneEightyApplication.class, args);
    }

}
