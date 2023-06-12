package com.example.java.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/test")
public class testCollecter {
    @GetMapping("/aspect")
    @testAspect
    public  String testAspect(){
        System.out.println("collecter方法");
        return "testAspect";
    }
}
