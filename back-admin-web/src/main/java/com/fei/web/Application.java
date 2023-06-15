package com.fei.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.fei"})
@MapperScan("com.fei.dao")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
