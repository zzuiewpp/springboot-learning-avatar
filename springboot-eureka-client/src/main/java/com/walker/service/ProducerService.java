package com.walker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-12 19:19
 */
@RestController
@RequestMapping("/producer")
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @GetMapping("/getUserName")
    public String getUserName(String name) {
        logger.info("producer provide method, param name:" + name);
        return name;
    }
}
