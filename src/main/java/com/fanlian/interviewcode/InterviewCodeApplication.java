package com.fanlian.interviewcode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fanlian.interviewcode.mapper")
public class InterviewCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterviewCodeApplication.class, args);
    }

}
