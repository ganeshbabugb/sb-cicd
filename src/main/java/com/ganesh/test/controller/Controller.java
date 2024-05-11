package com.ganesh.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @GetMapping("/hello")
    public Map<String, String> test() {
        return Map.of("Testing", "Working Fine!");
    }
}
