package com.ganesh.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping()
    public String home() {
        return "Hello Ganesh!";
    }

    @GetMapping("/test")
    public Map<String, String> test() {
        return Map.of("Testing", "Working Fine Ganesh!");
    }

    @GetMapping(value = "/test2", params = "v=2")
    public Map<String, String> test2() {
        return Map.of("Testing Version 2", "Working Fine Ganesh babu!");
    }
}
