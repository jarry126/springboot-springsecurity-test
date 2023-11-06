package com.bear.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('hello')")
    @PreAuthorize("hasAnyRole('hello')")
    public String hello(){
        return "hello";
    }
}
