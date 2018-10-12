package com.walker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-12 19:52
 */
@RestController
@RequestMapping("/consumer")
@Configuration
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/getUserName")
    public String getUserName(String name) {
        logger.info("consumer invoke method...");
        RestTemplate restTemplate = getRestTemplate();
        return restTemplate.getForObject("http://springboot-eureka-client/producer/getUserName?name=" + name, String.class);
    }
}
