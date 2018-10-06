package com.walker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-06 19:09
 */
@SpringBootApplication
@MapperScan("com.walker.dao")
public class MyBatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyBatisApplication.class, args);
    }
}
