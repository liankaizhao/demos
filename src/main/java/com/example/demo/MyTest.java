package com.example.demo;

import com.example.demo.aspect.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/zlc")
public class MyTest {


    @Operation(value = "查询")
    @GetMapping("/test")
    public String test() {
        System.out.println("这是我的测试方法");
        return "test";
    }


}
