package com.walker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-03 19:21
 */

@SpringBootApplication
@EnableAspectJAutoProxy
public class AopApplication {
    public static void main(String[] args){
        SpringApplication.run(AopApplication.class, args);
    }
}
