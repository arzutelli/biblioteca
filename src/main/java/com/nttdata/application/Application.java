package com.nttdata.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.nttdata")
@EntityScan("com.nttdata.model")
@MapperScan("com.nttdata.database")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

