package com.example.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {


    @Pointcut(value = "@annotation(operation)")
    public void showLog(Operation operation) {
    }


    @Before("showLog(operation)")
    public void beforeService(Operation operation) {
        System.out.println("方法执行前"+operation.value());

    }

    @After("showLog(operation)")
    public void afterService(Operation operation) {

        System.out.println("方法执行完成"+operation.value());
        log.info("这是我的测试");

    }






}