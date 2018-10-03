package com.walker.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author walker
 * @version 1.0
 * @since 2018-10-03 18:21
 */

@RestController
@RequestMapping("/user")
public class DemoService {

    @PostMapping("/reading")
    public void reading() {
        System.out.println("user begin reading...");
    }

    @GetMapping("/palying")
    public void palying() {
        System.out.println("user begin palying...");
    }
}
