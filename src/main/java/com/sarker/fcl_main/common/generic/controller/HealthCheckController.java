package com.sarker.fcl_main.common.generic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/health")
public class HealthCheckController {

    @GetMapping
    public String getHealth() {
        return "OK";
    }

    @GetMapping("/test")
    public String getTestValue() {

        // include the test case that I want to check
        return "Test OK";
    }
}
